let timer = null;
let searchWord = '';

$('#search-food').on('keyup', function () {

    let searchText = $(this).val();

    if (searchText.length > 2) {
        searchWord = searchText;
        clearTimeout(timer);
        timer = setTimeout(searchFoods, 500);
    }
});

function searchFoods() {
    searchLoader.show();
    fetch(URLS.searchFoods + '?searchWord=' + searchWord)
        .then(handleResponse)
        .then(displayFoodsList)
        .catch(handleError);
}

const displayFoodsList = function (foods) {
    let $foodsList = $('#foods-list');
    $foodsList.html('');

    if (foods.length === 0) {
        $foodsList.append('<h4>No results found</h4>');
        searchLoader.hide();
        return;
    }

    let columns = '';
    foods.forEach(food => {
        columns += `<tr><td>${food.name}</td></tr>`
    });

    $foodsList.append(columns);
    searchLoader.hide();
};