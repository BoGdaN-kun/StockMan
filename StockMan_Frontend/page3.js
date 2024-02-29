// Function to fetch data from the endpoint based on custom time interval
function fetchData(startDate, endDate) {
    const url = `http://localhost:8080/stockHistories/SM/${startDate}/${endDate}`;
    return fetch(url)
        .then(response => response.json())
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}

// Event listener for form submission
document.getElementById('date-range-form').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Get start and end dates from input fields
    const startDate = document.getElementById('start-date').value;
    const endDate = document.getElementById('end-date').value;

    // Fetch data based on custom time interval
    fetchData(startDate, endDate)
        .then(data => {
            const seriesData = data.map(item => ({
                x: new Date(item.date),
                y: [item.open, item.high, item.low, item.close]
            }));

            // Configure chart options and render
            const options = {
                chart: {
                    type: 'area',
                    height: 350,
                    width: '100%',
                    toolbar: {
                        show: false
                    }
                },
                series: [{
                    name: 'OHLC',
                    data: seriesData
                }],
                xaxis: {
                    type: 'datetime'
                },
                dataLabels: {
                    enabled: false
                },
                stroke: {
                    curve: 'smooth'
                },
                title: {
                    text: 'Area Chart with Datetime X-Axis - Dynamic Data',
                    align: 'left'
                },
                grid: {
                    row: {
                        colors: ['#f3f3f3', 'transparent'],
                        opacity: 0.5
                    }
                }
            };

            // Initialize and render chart
            const chart = new ApexCharts(document.getElementById('chart'), options);
            chart.render();
        });
});
