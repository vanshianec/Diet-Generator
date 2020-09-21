let ctxPriceBreakdownChart = $('#price-breakdown-chart');
let ctxCaloriesBreakdownChart = $('#calories-breakdown-chart');
let ctxAddFoodChart = $('#addFoodChart');

const formatAsPercentageMacros = function (tooltipItem, data) {

    return formatAsPercentage(tooltipItem, data, "");
};

const formatAsPercentagePrice = function (tooltipItem, data) {
    return formatAsPercentage(tooltipItem, data, "lv.");
};

const formatTitle = function (tooltipItem, data) {
    return data.labels[tooltipItem[0].index];
};

const formatAsPercentage = function (tooltipItem, data, additionalText) {
    let dataset = data.datasets[tooltipItem.datasetIndex];
    let total = dataset.data.reduce((acc, v) => acc + v, 0);
    let currentValue = dataset.data[tooltipItem.index];
    let percentage = parseFloat((currentValue / total * 100).toFixed(1));
    let currentValueFormat;
    if (additionalText === 'lv.') {
        currentValueFormat = currentValue.toFixed(2);
    } else {
        currentValueFormat = currentValue.toFixed(1);
    }

    return `${currentValueFormat}${additionalText} (${percentage}%)`;
};

let caloriesBreakdownData = {
    labels: ['Protein', 'Carbs', 'Fat'],
    datasets: [{
        label: 'Percentages',
        data: [0, 0, 0],
        backgroundColor: [
            'rgba(92, 184, 92, 0.8)',
            'rgba(2, 117, 216, 0.8)',
            'rgba(217, 83, 79, 0.8)',
        ],
        borderColor: [
            'rgba(92, 184, 92, 1)',
            'rgba(2, 117, 216, 1)',
            'rgba(217, 83, 79, 1)',
        ],
        borderWidth: 1
    }]
};

let caloriesBreakdownOptions = {
    legend: {
        display: false
    },
    animation: {
        duration: 0
    },
    cutoutPercentage: 70,
    responsive: true,
    tooltips: {
        backgroundColor: 'rgba(0, 0, 0, 1)',
        displayColors: false,
        callbacks: {
            label: formatAsPercentageMacros,
            title: formatTitle
        }
    }
};


let priceBreakdownOptions = JSON.parse(JSON.stringify(caloriesBreakdownOptions));
priceBreakdownOptions.tooltips.callbacks.label = formatAsPercentagePrice;
let priceBreakdownData = JSON.parse(JSON.stringify(caloriesBreakdownData));
priceBreakdownData.labels = [];
priceBreakdownData.datasets[0].data = [];
priceBreakdownData.datasets[0].backgroundColor = [];
priceBreakdownData.datasets[0].borderColor = [];


let caloriesBreakdownChart = new Chart(ctxCaloriesBreakdownChart, {
    type: 'doughnut',
    data: caloriesBreakdownData,
    options: caloriesBreakdownOptions
});

let priceBreakdownChart = new Chart(ctxPriceBreakdownChart, {
    type: 'doughnut',
    data: priceBreakdownData,
    options: priceBreakdownOptions
});

let addFoodToDiaryChart = new Chart(ctxAddFoodChart, {
    type: 'doughnut',
    data: caloriesBreakdownData,
    options: caloriesBreakdownOptions
});