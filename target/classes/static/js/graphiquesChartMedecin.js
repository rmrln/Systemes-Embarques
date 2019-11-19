    //Temperature Chart
    var temperatures = [[${temperatures}]];
    var datesTemperatures = [[${datesTemperatures}]];
    var borderColorTemperatures =[[${borderColorTemperatures}]];
    var sizePointTemperatures = [[${sizePointTemperatures}]];
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
            xAxes : [{
                ticks: {
                    beginAtZero: true
                }
            }],
            yAxes: [{
                ticks: {
                    min: 35,
                    max: 40,
                    beginAtZero: true
                },
                scaleLabel: {
                    display: true,
                    labelString: 'Températures'
                }
            }]
        }
    };
    var chart_temperature = new Chart('temperaturesChart', {
        type: 'line',
        data: data_temperatures,
        options: options_temperatures
    });

    //Respiration Chart
    var respirations = [[${respirations}]];
    var datesRespirations = [[${datesRespirations}]];
    var borderColorRespirations =[[${borderColorRespirations}]];
    var sizePointRespirations = [[${sizePointRespirations}]];
    var data_respiration ={
        labels: datesRespirations,
        datasets: [{
            fill : false,
            label: 'Respiration du patient',
            data: respirations,
            borderColor: borderColorRespirations,
            pointRadius: sizePointRespirations,
            borderWidth: 1
        }]
    };
    var options_respirations = {
        scales: {
            xAxes : [{
                ticks: {
                    beginAtZero: true
                }
            }],
            yAxes: [{
                ticks: {
                    min: 0,
                    max: 60,
                    beginAtZero: true
                },
                scaleLabel: {
                    display: true,
                    labelString: 'Flux d air'
                }
            }]
        }
    };

    var chart_respirations = new Chart('respirationsChart', {
        type: 'line',
        data: data_respiration,
        options: options_respirations
    });

    // Graphique positions
    var positions = [[${positions}]];
    var datesPositions = [[${datesPositions}]];
    var borderColorPositions =[[${borderColorPositions}]];
    var sizePointPositions = [[${sizePointPositions}]];
    var data_positions ={
        labels: datesPositions,
        yLabels: ['Debout', 'Allongé'],
        datasets: [{
            fill : false,
            label: 'Position du patient',
            steppedLine: true,
            data: positions,
            borderColor: borderColorPositions,
            pointRadius: sizePointPositions,
            borderWidth: 1
        }]
    };
    var options_positions = {
        scales: {
            xAxes : [{
                ticks: {
                    beginAtZero: true
                }
            }],
            yAxes: [{
                display: true,
                position: 'left',
                type: 'category',
                ticks: {
                    min: 0,
                    max: 1,
                    stepSize: 1,
                    reverse: false
                },
                scaleLabel: {
                    display: true,
                    labelString: 'Positions'
                }
            }]
        }
    };

    var chart_positions = new Chart('positionsChart', {
        type: 'line',
        data: data_positions,
        options: options_positions
    });