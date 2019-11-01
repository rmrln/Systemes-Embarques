var ctx = $("#myChart").get(0).getContext("2d");

var data = {
    labels: [
        "Red",
        "Blue",
        "Yellow"
    ],
    datasets: [
        {
            data: [300, 50, 100],
            backgroundColor: [
                "#FF6384",
                "#4BC0C0",
                "#FFCE56"
            ],
            label: 'My dataset'
        }]
};
var myPieChart = new Chart(ctx,{
    type: 'pie',
    data: data,
});