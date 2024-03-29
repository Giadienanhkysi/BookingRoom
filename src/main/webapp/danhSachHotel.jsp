
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
        <link rel="stylesheet" href="assets/css/danhSachHotel.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
    </head>
    <body>
        <div class="main">
            <header class="header">
                <div class="grid wide">
                    <div class="row">
                        <div class="col l-6 m-5 c-5 logo-and-search">                        
                            <h1><a href='<c:url value="/trang-chu"/>' class="logo">Booking Room</a></h1>

                            <div class="header-search-box">
                                <div class="search-wrapper">
                                    <div class="search-location">
                                        <i class="search-icon fas fa-search"></i>
                                        <input type="text" placeholder="Tìm kiếm" class="search-location__input">
                                    </div>                        

                                    <div class="book-date">
                                        <i class="book-date__icon far fa-calendar"></i>
                                        <span>Ngày</span>
                                    </div>

                                    <div class="book-guest">
                                        <i class="book-guest__icon fas fa-user"></i>
                                        <span>Số khách</span>
                                    </div>

                                    <button class="search-btn">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </div>

                        <nav class="navbar">
                            <ul class="navbar__menu">                            
                                <li class="navbar__menu-item"><a href='<c:url value="/trang-chu"/>'>Trang chủ</a></li>
                                <c:if test="${not empty USER}">
                                    <li class="navbar__menu-item"><a class="welcome-user" userId="${USER.id}" href="#">Chào, ${USER.firstname}</a></li>
                                    <li class="navbar__menu-item"><a href='<c:url value="/thoat?action=logout"/>'>Đăng xuất</a></li>
                                </c:if>
                                
                                <c:if test="${empty USER}">
                                    <li class="navbar__menu-item"><a href='<c:url value="/dang-nhap?action=login"/>'>Đăng nhập</a></li>
                                    <li class="navbar__menu-item"><a href="register.jsp">Đăng ký</a></li>
                                </c:if>
                            </ul>
                            <div class="mobile-menu">
                                <label for="menu-checkbox" class="mobile-menu__btn">
                                    <i class="fas fa-bars"></i>
                                </label>
                                <input class="menu-checkbox" id="menu-checkbox" type="checkbox">
                                <label for="menu-checkbox" class="overlay"></label>
                                <div class="mobile-menu__table">
                                    <div class="mobile-menu__header">
                                        <h1>MENU</h1>
                                        <label for="menu-checkbox" class="mobile-menu__close">
                                            <i class="fas fa-times"></i>
                                        </label>                                    
                                    </div>
                                    <ul class="mobile-menu__list">                                    
                                        <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link" href='<c:url value="/trang-chu"/>'>Trang chủ</a></li>                                    
                                        <c:if test="${not empty USER}">
                                            <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link" id="welcome-user" userId="${USER.id}" href="#">Chào, ${USER.firstname}</a></li>
                                            <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link" href='<c:url value="/thoat?action=logout"/>'>Đăng xuất</a></li>                                    
                                        </c:if>
                                        <c:if test="${empty USER}">
                                            <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link" href='<c:url value="/dang-nhap?action=login"/>'>Đăng nhập</a></li>
                                            <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link" href="register.jsp">Đăng ký</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>                        
                        </nav>
                    </div>                


                </div>

            </header>

            <div class="container">
                <div class="grid wide">                
                    <div class="row">  
                        <div class="col l-6 thanhPho"></div>
                    </div>
                    <div class="row hotelList">
                        <h1 style="margin: auto;margin-bottom: 100%;">Không tìm thấy trang yêu cầu</h1>
                    </div>


                </div>                                    
            </div>
            <ul class="phan-trang khach-san__phan-trang">
                <li class="phan-trang-item">
                    <a href="" class="phan-trang-item__link">
                        <i class="phan-trang-item__icon fas fa-angle-left"></i>
                    </a>
                </li>
                <li class="phan-trang-item phan-trang-item--active">
                    <a href="" class="phan-trang-item__link">1</a>
                </li>
                <li class="phan-trang-item">
                    <a href="" class="phan-trang-item__link">2</a>
                </li>
                <li class="phan-trang-item">
                    <a href="" class="phan-trang-item__link">3</a>
                </li>
                <li class="phan-trang-item">
                    <a href="" class="phan-trang-item__link">4</a>
                </li>
                <li class="phan-trang-item">
                    <a href="" class="phan-trang-item__link">...</a>
                </li>
                <li class="phan-trang-item">
                    <a href="" class="phan-trang-item__link">14</a>
                </li>
                <li class="phan-trang-item">
                    <a href="" class="phan-trang-item__link">
                        <i class="phan-trang-item__icon fas fa-angle-right"></i>
                    </a>
                </li>
            </ul>

            <div class="footer__bottom">
                <div class="grid wide"></div>
                <div class="row">
                    <div class="col l-12">
                        <p>© 2022 Lập trình Website đặt phòng khách sạn</p>                        
                    </div>
                </div>
            </div>
            <script src="assets/js/base.js" type="module"></script>
            <script src="assets/js/danhSachHotel.js" type="module"></script>
        </div>
    </body>
</html>
