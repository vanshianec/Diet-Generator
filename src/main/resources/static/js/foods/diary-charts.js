let ctx = $('#myChart');
let ctx2 = $('#myChart2');
let ctxAddFoodChart = $('#addFoodChart');

const formatAsPercentage = function (tooltipItem, data) {
    let dataset = data.datasets[tooltipItem.datasetIndex];
    let total = dataset.data.reduce((acc, v) => acc + v, 0);
    let currentValue = dataset.data[tooltipItem.index];
    let percentage = parseFloat((currentValue / total * 100).toFixed(1));
    return currentValue + ' (' + percentage + '%)';
};

let data = {
    labels: ['Protein', 'Carbs', 'Fat'],
    datasets: [{
        label: 'Percentages',
        data: [180, 220, 45],
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

let options = {
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
            label: formatAsPercentage,
            title: function (tooltipItem, data) {
                return data.labels[tooltipItem[0].index];
            }
        }
    }
};

let myChart = new Chart(ctx, {
    type: 'doughnut',
    data: data,
    options: options
});

let myChart2 = new Chart(ctx2, {
    type: 'doughnut',
    data: data,
    options: options
});

let addFoodToDiaryChart = new Chart(ctxAddFoodChart, {
    type: 'doughnut',
    data: data,
    options: options
});