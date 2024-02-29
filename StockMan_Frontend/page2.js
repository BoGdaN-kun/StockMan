document.addEventListener("DOMContentLoaded", function() {
    // Function to fetch data from the API based on the specified time range
    function fetchData(startDate, endDate) {
        // Construct the URL with the start and end dates
        const endpointUrl = `http://localhost:8080/stockHistories/SM/${startDate}/${endDate}`;

        // Fetch data from the API
        return fetch(endpointUrl)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // Parse JSON response
            })
            .then(data => data) // Return the fetched data
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    // Function to render the chart with the provided data
    function renderChart(chartData) {
        var options = {
            series: [{
                data: chartData
            }],
            chart: {
                id: 'area-datetime',
                type: 'area',
                height: 350,
                zoom: {
                    autoScaleYaxis: true
                }
            },
            dataLabels: {
                enabled: false
            },
            markers: {
                size: 0,
                style: 'hollow',
            },
            xaxis: {
                type: 'datetime',
                tickAmount: 6,
            },
            tooltip: {
                x: {
                    format: 'dd MMM yyyy'
                }
            },
            fill: {
                type: 'gradient',
                gradient: {
                    shadeIntensity: 1,
                    opacityFrom: 0.7,
                    opacityTo: 0.9,
                    stops: [0, 100]
                }
            },
        };

        var chart = new ApexCharts(document.querySelector("#chart-timeline"), options);
        chart.render();
    }

    // Event listener for the form submission
    document.querySelector("#date-range-form").addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission behavior

        // Get the start and end dates from the form
        const startDate = document.querySelector("#start-date").value;
        const endDate = document.querySelector("#end-date").value;

        // Fetch data from the API based on the specified time range
        fetchData(startDate, endDate)
            .then(data => {
                // Transform data to match chart format
                const chartData = data.map(item => [new Date(item.date).getTime(), item.value]);
                // Render the chart with the fetched data
                renderChart(chartData);
            });
    });
});
