document.addEventListener('DOMContentLoaded', function () {
    const addEmpForm = document.getElementById('add-emp-form');

    addEmpForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const id = document.getElementById('id').value;
        const name = document.getElementById('emp-name').value;
        const role = document.getElementById('role').value;
        const salary = document.getElementById('salary').value;

        const formData = {
            id: parseInt(id),
            name: name,
            role: role,
            salary: parseFloat(salary)
        };

        fetch('/add-employee', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Failed to add employee');
                }
            })
            .then(data => {
                alert(data.message);
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});
