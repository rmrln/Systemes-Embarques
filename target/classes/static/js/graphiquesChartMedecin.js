var ctx = document.getElementById('temperaturesChart').getContext('2d');


var data =
    /*{
    labels: generateLabels(),
    datasets: [{
        backgroundColor: utils.transparentize(presets.red),
        borderColor: presets.red,
        data: generateData(),
        hidden: true,
        label: 'D0'
    }, {
        backgroundColor: utils.transparentize(presets.orange),
        borderColor: presets.orange,
        data: generateData(),
        label: 'D1',
        fill: '-1'
    }, {
        backgroundColor: utils.transparentize(presets.yellow),
        borderColor: presets.yellow,
        data: generateData(),
        hidden: true,
        label: 'D2',
        fill: 1
    }, {
        backgroundColor: utils.transparentize(presets.green),
        borderColor: presets.green,
        data: generateData(),
        label: 'D3',
        fill: '-1'
    }, {
        backgroundColor: utils.transparentize(presets.blue),
        borderColor: presets.blue,
        data: generateData(),
        label: 'D4',
        fill: '-1'
    }, {
        backgroundColor: utils.transparentize(presets.grey),
        borderColor: presets.grey,
        data: generateData(),
        label: 'D5',
        fill: '+2'
    }, {
        backgroundColor: utils.transparentize(presets.purple),
        borderColor: presets.purple,
        data: generateData(),
        label: 'D6',
        fill: false
    }, {
        backgroundColor: utils.transparentize(presets.red),
        borderColor: presets.red,
        data: generateData(),
        label: 'D7',
        fill: 8
    }, {
        backgroundColor: utils.transparentize(presets.orange),
        borderColor: presets.orange,
        data: generateData(),
        hidden: true,
        label: 'D8',
        fill: 'end'
    }]
};*/

{
var ctx = document.getElementById('temperatures').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ['16/10/19', '17/10/19', '18/10/19', '19/10/19', '20/10/19', '21/10/19'],
        datasets: [{
            fill : false,
            label: 'Température du patient',
            data: [36.7, 36.5, 36.6, 36.8, 36.4, 36.6],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
        }]
<<<<<<< HEAD
    },
    options: {
        scales: {
            xAxew : [{
                ticks: {
                    beginAtZero: true
                }
            }],
            yAxes: [{
                ticks: {
                    min: 35,
                    max: 40,
                    beginAtZero: true
                }
            }]
        }
    }
});
=======
    };

var options = {
    scales: {
        xAxew : [{
            ticks: {
                beginAtZero: true
            }
        }],
        yAxes: [{
            ticks: {
                min: 35,
                max: 40,
                beginAtZero: true
            }
        }]
    }
};

var chart = new Chart(ctx, {
    type: 'line',
    data: data,
    options: options
});
>>>>>>> origin/master
