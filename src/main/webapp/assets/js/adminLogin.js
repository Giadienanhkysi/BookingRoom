import Validator from './validator.js';
import{ host } from './base.js';
const url = {
    admin: "dang-nhap-quan-ly?action=login"
}
let validator = new Validator({
    form: "#form-1",
    errorSelector: ".form-message",
    formGroupSelector: ".form-group",
    rules: [
        Validator.isRequire("#username", "Vui lòng nhập tài khoản"),
        Validator.minLength("#password", 8),
    ],
    onSubmit: function (value) {
        let ad = {
             username: value.username,
             password: value.password
         }
         let admin = JSON.stringify(ad)
         
         fetch(host + url.admin, {
            method: "POST",
            body: admin,
            redirect: 'follow'
         })
         .then(response => {
         if (response.redirected) {
             window.location.href = response.url;
         }
         })
         .catch(function(err) {
             console.info(err + " url: " + url);
         });
         
     },
});
validator.execute();