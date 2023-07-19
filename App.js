document.getElementById('submitButton').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent form submission to avoid page reload
    uploadFile();
});

async function uploadFile() {
    const imageInput = document.getElementById('file-input');

    if (imageInput.files.length === 0) {
        alert('Please select an image.');
        return;
    }

    const file = imageInput.files[0];
    const formData = new FormData();
    formData.append('image', file);

    try {
        const response = await fetch('http://54.211.46.55:5000/upload', {
            method: 'POST',
            body: formData,
        });

        if (!response.ok) {
            throw new Error('Upload failed');
        }

        const fileName = await response.text();
        console.log('Uploaded file name:', fileName);
        alert('File uploaded. Server response: ' + fileName);
    } catch (error) {
        console.error('Error during file upload:', error);
    }
}
