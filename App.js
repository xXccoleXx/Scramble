document.getElementById('submitButton').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent form submission to avoid page reload

    const imageInput = document.getElementById('file-input');

    if (imageInput.files.length === 0) {
        alert('Please select an image.');
        return;
    }

    const file = imageInput.files[0];
    const formData = new FormData();
    formData.append('image', file);
    // alert(file.name + ' ' + file.imageInput);
    

    const response = fetch('http://54.211.46.55:5000/upload', {
        // mode: 'no-cors',
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
        // return response.text();
    })
    .then(data => {
        console.log(data); // Log the JSON response to the console
        alert('Uploaded file name: ' + file.name + '\n' + data.message);
    })
    .catch(error => {
        console.log(data); 
        alert('Error: ' + error.message);
    });
});
