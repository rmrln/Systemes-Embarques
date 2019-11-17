var temperatures = [[${temperatures}]];
var datesTemperatures = [[${datesTemperatures}]];
var borderColorTemperatures =[[${borderColorTemperatures}]];
var sizePointTemperature = [[${sizePointTemperature}]];
var ctx = document.getElementById('temperaturesChart').getContext('2d');

var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: datesTemperatures,
        datasets: [{
            fill : false,
            label: 'Température du patient',
            data: temperatures,
            borderColor: borderColorTemperatures,
            pointRadius: sizePointTemperature,
            borderWidth: 1
        }]
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


