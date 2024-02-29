document.addEventListener("DOMContentLoaded", function() {
    const fetchDataBtn = document.getElementById("fetchDataBtn");
    fetchDataBtn.addEventListener("click", fetchData);

    function fetchData() {
        const startDate = document.getElementById("startDate").value;
        const endDate = document.getElementById("endDate").value;
        const endpointUrl = `http://localhost:8080/stockHistories/SM/${startDate}/${endDate}`;

        // Fetch JSON data
        fetch(endpointUrl)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // Parse JSON response
            })
            .then(data => {
                // Handle the received JSON data
                const chartData = transformDataForChart(data);
                renderChart(chartData);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    // Function to transform JSON data for chart
    function transformDataForChart(data) {
        return data.map(item => ({
            x: new Date(item.date),
            y: [item.open, item.high, item.low, item.close]
        }));
    }

    // Function to render the chart using ApexCharts
    function renderChart(chartData) {
        var options = {
            series: [{
                data: chartData
            }],
            chart: {
                type: 'candlestick',
                height: 350
            },
            xaxis: {
                type: 'datetime'
            },
            plotOptions: {
                candlestick: {
                    wick: {
                        useFillColor: true,
                    }
                }
            },
        };

        var chart = new ApexCharts(document.querySelector("#chart-container"), options);
        chart.render();
    }
});
