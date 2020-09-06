let currentSortType = 'none';
let currentFoodGroup = 'none';
let currentSortParam = '';
let areAllFoodsLoaded = false;
let pageCount = 0;

const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const URLS = {
    foods: '/api/foods',
};

$(window).on('load', function () {
    loadFoods('none', 'none');
});

$(window).scroll(function () {
    /* if the bottom of the page is reached */
    if ($(window).scrollTop() === $(document).height() - $(window).height()) {
        loadFoods(currentSortType, currentFoodGroup, currentSortParam);
    }
});

const loadFoods = function (sortType, foodGroup, sortParam) {

    if (currentSortType !== sortType) {
        pageCount = 0;
        areAllFoodsLoaded = false;
        currentSortType = sortType;
        currentSortParam = sortParam;
        currentFoodGroup = foodGroup;
        $('#foods-columns').html('');
    }

    let url = URLS.foods + '?page=' + pageCount + '&foodGroup=' + foodGroup;

    if (sortType !== 'none') {
        url += '&sort=' + sortParam + ',' + sortType;
    }

    if (!areAllFoodsLoaded) {
        loader.show();
        fetch(url)
            .then(handleResponse)
            .then(addFoodsToTemplate)
            .catch(handleError);
    }
};

const handleResponse = function (response) {

    if (!response.ok) {
        throw new Error('Network response was not ok');
    }

    return response.json();
};

const addFoodsToTemplate = function (foods) {
    if (foods.length === 0) {
        areAllFoodsLoaded = true;
        //TODO add end of page html
    }

    let result = '';
    foods.forEach(food => {
        const itemString = toString(food);
        result += itemString;
    });
    $('#foods-columns').append(result);
    pageCount++;
    loader.hide();
};

const handleError = function (error) {
    //TODO do some error handling
    console.error(error);
    loader.hide();
};

const toString = function ({name, foodGroup, calories, fat, carbohydrates, protein}) {
    let columns = `<td>${name}</td>
                   <td>${foodGroup}</td>
                   <td>${calories}</td>
                   <td>${carbohydrates}</td>
                   <td>${fat}</td>
                   <td>${protein}</td>`;

    return `<tr>${columns}</tr>`
};

//TODO split code into different files

let $sortable = $('.sortable');

$sortable.on('click', function () {

    let $this = $(this);
    let asc = $this.hasClass('asc');
    let desc = $this.hasClass('desc');
    $sortable.removeClass('asc').removeClass('desc');
    if (!asc && !desc) {
        $this.addClass('asc');
    } else if (asc) {
        $this.addClass('desc');
    }

    asc = $this.hasClass('asc');
    desc = $this.hasClass('desc');
    let sortType = 'none';
    let sortParam = toCamelCase($this.text());

    if (asc) {
        sortType = 'asc';
    } else if (desc) {
        sortType = 'desc';
    }

    loadFoods(sortType, currentFoodGroup, sortParam);
});

const toCamelCase = function (str) {
    return str.replace(/(?:^\w|[A-Z]|\b\w)/g, function (word, index) {
        return index === 0 ? word.toLowerCase() : word.toUpperCase();
    }).replace(/\s+|-/g, '');
};

$('#choose-food-group').on('change', function () {
    let foodGroup = $(this).val();
    loadFoods(currentSortType, foodGroup, currentSortParam);
});
