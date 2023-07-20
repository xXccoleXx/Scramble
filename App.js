document.getElementById('submitButton').addEventListener('click', function (event) {
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

        const scrambledImgName = await response.text(); // Assuming the response is the image name (e.g., "12345678.png")
        const scrambledImgUrl = `https://s3.amazonaws.com/scrambledlikesumeggs/${scrambledImgName}`;

        // Display the scrambled image on the page
        const scrambledImg = document.createElement('img');
        scrambledImg.src = scrambledImgUrl;
        const scrambledDiv = document.getElementById('scrambled');
        scrambledDiv.innerHTML = ''; // Clear any existing content
        scrambledDiv.appendChild(scrambledImg);

        // alert('File uploaded. Scrambled image URL: ' + scrambledImgUrl);
    } catch (error) {
        console.error('Error during file upload:', error);
    }
}
