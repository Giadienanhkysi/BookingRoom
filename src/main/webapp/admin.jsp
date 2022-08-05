<%-- 
    Document   : admin
    Created on : Jun 21, 2022, 5:36:10 PM
    Author     : hensh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.15.4-web/css/all.min.css">
        <link rel="stylesheet" href="assets/css/grid.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/admin.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <title>Quản lý trang web</title>
    </head>
    <body>
        <div class="main">
        <header class="header">
            <div class="grid wide">
                <div class="row">
                    <div class="col l-6 m-5 c-5  logo-and-search">
                        <h1><a class="login-logo" href='<c:url value="/trang-chu"/>'> Booking Room</a></h1>
                    </div>
                    <nav class="navbar">
                        <ul class="navbar__menu">
                            <c:if test="${not empty ADMIN}">
                                <li class="navbar__menu-item"><a class="welcome-user" adminId="${ADMIN.id}" href="#">Chào, ${ADMIN.first_name}</a></li>
                                <li class="navbar__menu-item"><a href='<c:url value="/dang-xuat-quan-ly?action=logout"/>'>Đăng xuất</a></li>                                    
                            </c:if>
                            <c:if test="${empty ADMIN}">
                                <li class="navbar__menu-item"><a href='<c:url value="/dang-nhap?action=login"/>'>Đăng nhập</a></li>
                            </c:if>
                        </ul>
                        <div class="mobile-menu">
                            <menu-label for="menu-checkbox" class="mobile-menu__btn">
                                <i class="fas fa-bars"></i>
                            </menu-label>
                            <input class="menu-checkbox" id="menu-checkbox" type="checkbox">
                            <menu-label for="menu-checkbox" class="overlay"></menu-label>
                            <div class="mobile-menu__table">
                                <div class="mobile-menu__header">
                                    <h1>MENU</h1>
                                    <menu-label for="menu-checkbox" class="mobile-menu__close">
                                        <i class="fas fa-times"></i>
                                    </menu-label>
                                </div>
                                <ul class="mobile-menu__list">
                                    <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link"
                                            href="">Trang chủ</a></li>
                                    <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link"
                                            href="register.html">Đăng ký</a></li>
                                    <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link"
                                            href="login.html">Đăng nhập</a></li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>


            </div>
        </header>


        <div class="container">
            <div class="grid wide">
                <!-- <div class="left-menu-wrapper"> -->
                <div class="left-menu">
                    <div class="quan-ly-item quan-ly-trang-chu">
                        <span class="menu-label">Quản lý trang chủ</span>
                    </div>
                    <div class="quan-ly-item quan-ly-khach-san">
                        <span class="menu-label">Quản lý khách sạn</span>
                    </div>
                    <div class="quan-ly-item quan-ly-dat-phong">
                        <span class="menu-label">Quản lý đặt phòng</span>
                    </div>
                </div>
                <!-- </div> -->
                <div class="row">
                    <div class="col l-2 c-4 m-4">
                    </div>
                    <div class="content col l-10 c-8 m-8">
                        

                    </div>
                </div>
            </div>

        </div>
        <div class="overlay quan-ly">
            <div class="row quan-ly__pop-up">
                
            </div>
        </div>
       
    </div>
    </div>

    <script src="assets/js/admin.js" type="module"></script>
    </body>
</html>
