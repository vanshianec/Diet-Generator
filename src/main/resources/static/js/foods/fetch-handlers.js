const handleResponse = function (response) {

    if (!response.ok) {
        throw new Error('Network response was not ok');
    }

    return response.json();
};


const handleError = function (error) {
    //TODO do some error handling
    pageLoader.hide();
    searchLoader.hide();
};