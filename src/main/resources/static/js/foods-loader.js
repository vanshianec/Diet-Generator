$(window).scroll(function () {
    /* if the bottom of the page is reached */
    if ($(window).scrollTop() === $(document).height() - $(window).height()) {
        loader.show();
        fetch(URLS.foods)
            .then(response => response.json())
            .then(foods => {
                let result = '';
                foods.forEach(food => {
                    const itemString = toString(food);
                    result += itemString;
                });

                $('#items-table').html(result);
                loader.hide();
            });
    }
});

const loader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const URLS = {
    foods: '/foods',
};

