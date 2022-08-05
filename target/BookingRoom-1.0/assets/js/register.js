import { host } from './base.js';
import Validator from './validator.js';
const url = {
    user: "api-user",
    login: "dang-nhap"
}
let validator = new Validator({
    form: "#form-1",
    errorSelector: ".form-message",
    formGroupSelector: ".form-group",
    rules: [
        Validator.isRequire('#username', "Không để trống"),
        Validator.isRequire("#firstname", "Vui lòng nhập tên"),
        Validator.isCharacter("#firstname", "Vui lòng nhập chữ"),
        Validator.isRequire("#lastname", "Vui lòng nhập họ tên đệm"),
        Validator.isCharacter("#lastname", "Vui lòng nhập chữ"),
        Validator.isRequire("#email", "Không để trống"),
        Validator.isEmail("#email", "Vui lòng nhập đúng email"),
        Validator.isRequire("#phone", "Nhập số điện thoại"),
        Validator.isPhoneNum("#phone", "Vui lòng nhập đúng số điện thoại"),
        Validator.minLength("#password", 8),
        Validator.isRequire("#password--confirm", "Không để trống"),
        Validator.isRequire('input[name="gender"]', "Vui lòng chọn"),
        Validator.isConfirmed(
                "#password--confirm",
                function () {
                    return document.querySelector("#form-1 #password").value;
                },
                "Mật khẩu nhập lại không chính xác"
                ),
    ],
    onSubmit: function (value) {
        let us = {
            username: value.username,
            password: value.password,
            email: value.email,
            firstname: value.firstname,
            gender: value.gender,
            lastname: value.lastname,
            phone: value.phone,
            gender: value.gender[0],
            address: "",

        }
        let user = JSON.stringify(us)
        console.log(user)
        fetch(host + url.user, {
            method: "POST",
            body: user,

        })
        .then(response => response.json())
        .then(user => {
            if (user) {
                alert("Đăng ký thành công")
                fetch(host + "dang-nhap?action=login", {
                    method: "GET",
                    redirect: 'follow'
                })
                .then(response => {
                    if (response.redirected) {
                        window.location.href = response.url;
                    }
                })
                .catch(function (err) {
                    console.info(err + " url: " + url);
                });
            } else {
                alert("Đăng ký thất bại mời bạn đăng ký lại")
            }
        })
    },
});
validator.execute();