(function () {
    var filesExt = ['jpg', 'gif', 'png']; // массив расширений
    var forms = document.querySelectorAll('.needs-validation')
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if(filesExt.join().search(parts[parts.length - 1]) != -1){
                    event.preventDefault()
                    event.stopPropagation()
                }
                form.classList.add('was-validated')
            }, false)
        })
})()