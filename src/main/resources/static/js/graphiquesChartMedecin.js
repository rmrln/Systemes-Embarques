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
