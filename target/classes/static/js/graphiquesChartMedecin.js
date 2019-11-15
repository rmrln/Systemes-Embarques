//Get Thymeleaf DOM Object
var ctx_respirations = document.getElementById('respirationsChart').getContext('2d');
var ctx_temperatures  = document.getElementById('temperaturesChart').getContext('2d');
//Temperature Chart
var data_temperatures ={
    labels: datesTemperatures,
    datasets: [{
        fill : false,
        label: 'Température du patient',
        data: temperatures,
        borderColor: borderColorTemperatures,
        pointRadius: sizePointTemperatures,
        borderWidth: 1
    }]
};
var options_temperatures = {
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

var chart_temperature = new Chart(ctx_temperatures, {
    type: 'line',
    data: data_temperatures,
    options: options_temperatures
});

//Respiration Chart
var data_respiration ={
    labels: datesRespirations,
    datasets: [{
        fill : false,
        label: 'Température du patient',
        data: respirations,
        borderColor: borderColorRespirations,
        pointRadius: sizePointRespirations,
        borderWidth: 1
    }]
};
var options_respirations = {
    scales: {
        xAxew : [{
            ticks: {
                beginAtZero: true
            }
        }],
        yAxes: [{
            ticks: {
                min: 0,
                max: 100,
                beginAtZero: true
            }
        }]
    }
};

var chart_respirations = new Chart(ctx_respirations, {
    type: 'line',
    data: data_respiration,
    options: options_respirations
});

function getTemperatureBorderColor(temperatures) {
    let borderColorTemperatures = [];
    temperatures.forEach(function(temperature){
        if(temperature > 37.5) borderColorTemperatures.push('rgba(54, 162, 235, 1)');
        else borderColorTemperatures.push('rgba(255, 99, 132, 1)');
    });
    return borderColorTemperatures;
}