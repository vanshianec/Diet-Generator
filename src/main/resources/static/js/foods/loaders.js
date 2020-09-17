const pageLoader = {
    show: () => {
        $('#page-loader').show();
    },
    hide: () => {
        $('#page-loader').hide();
    },
};

const searchLoader = {
    show: () => {
        $('#search-loader').css('visibility', 'visible');
    },
    hide: () => {
        $('#search-loader').css('visibility', 'hidden');
    },
};