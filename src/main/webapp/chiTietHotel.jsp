
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.15.4-web/css/all.min.css">    
        <link rel="stylesheet" href="assets/css/grid.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/chiTietHotel.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <link rel="stylesheet" href="assets/css/lich.css">
        <title>Chi tiết khách sạn</title>
    </head>
    <body>
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
                <ul class="slide-list danh-sach-anh">

                </ul>
                <div class="noidung">
                    <div class="row gioi-thieu">

                    </div>
                    <div class="row dat-phong">
                        <div class="col l-4 m-4">
                            <h2 class="dat-phong__ngay-nhan">Ngày nhận phòng</h2>
                            <div class="checkIn__wrapper">
                                <button class="dat-phong__chon-ngay checkIn">
                                    <i class="fas fa-calendar-alt lich-icon"></i>
                                    <span class="ngay checkIn"></span>
                                </button>                            
                                <div class="calendar checkIn display-none">
                                    <div class="month checkIn">
                                        <i class="fas fa-angle-left prev checkIn"></i>
                                        <div class="date checkIn">
                                            <h1></h1>
                                            <p></p>
                                        </div>
                                        <i class="fas fa-angle-right next checkIn"></i>
                                    </div>
                                    <div class="weekdays">
                                        <div>Sun</div>
                                        <div>Mon</div>
                                        <div>Tue</div>
                                        <div>Wed</div>
                                        <div>Thu</div>
                                        <div>Fri</div>
                                        <div>Sat</div>
                                    </div>
                                    <div class="days checkIn"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col l-4 m-4">
                            <h2 class="dat-phong__ngay-tra">Ngày trả phòng</h2>
                            <div class="checkOut__wrapper">
                                <button class="dat-phong__chon-ngay checkOut">
                                    <i class="fas fa-calendar-alt lich-icon"></i>
                                    <span class="ngay checkOut"></span>
                                </button>
                                <div class="calendar checkOut display-none">
                                    <div class="month checkOut">
                                        <i class="fas fa-angle-left prev checkOut"></i>
                                        <div class="date checkOut">
                                            <h1></h1>
                                            <p></p>
                                        </div>
                                        <i class="fas fa-angle-right next checkOut"></i>
                                    </div>
                                    <div class="weekdays">
                                        <div>Sun</div>
                                        <div>Mon</div>
                                        <div>Tue</div>
                                        <div>Wed</div>
                                        <div>Thu</div>
                                        <div>Fri</div>
                                        <div>Sat</div>
                                    </div>
                                    <div class="days checkOut"></div>
                                </div>
                            </div>
                        </div>
                        <div class="col l-4 m-4">
                            <div class="tim-phong-btn-wrapper">
                                <button class="tim-phong-btn ">Tìm phòng</button>
                            </div>
                        </div>
                    </div>


                </div>            
                <div class="row danh-sach-phong">
                    <div class="col l-6 m-12 c-12">
                        <div class="phong-item">
                            <h1 class="phong__loai">Phòng Deluxe</h1>
                            <div class="phong__chitiet">
                                <div class="so-nguoi">
                                    <span>Phù hợp cho: </span>
                                    <i class="fas fa-user"></i>
                                    <i class="fas fa-user"></i>
                                </div>
                                <div class="giuong">
                                    <span>Giường đôi </span>
                                    <i class="fas fa-bed"></i>
                                </div>
                                <ul class="tien-ich">
                                    <li class="tien-ich-item">28 m2</li>
                                    <li class="tien-ich-item">Điều hòa</li>
                                    <li class="tien-ich-item">TV</li>
                                    <li class="tien-ich-item">Tủ lạnh</li>
                                    <li class="tien-ich-item">Ban công</li>
                                </ul>
                            </div>
                            <div class="phong__dat">
                                <div>                                
                                    <p class="gia-mota">Giá phòng</p>
                                    <h1 class="gia">450,000 VND</h1>
                                </div>
                                <select name="so-luong-phong" class="so-luong-phong">
                                    <option value="">Chọn số lượng phòng</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                                <button class="dat-phong-btn dat-btn">Đặt phòng</button>
                            </div>
                        </div>
                    </div>
                    <div class="col l-6 m-12 c-12">
                        <div class="phong-item">
                            <h1 class="phong__loai">Phòng Deluxe</h1>
                            <div class="phong__chitiet">
                                <div class="so-nguoi">
                                    <span>Phù hợp cho: </span>
                                    <i class="fas fa-user"></i>
                                    <i class="fas fa-user"></i>
                                </div>
                                <div class="giuong">
                                    <span>Giường đôi </span>
                                    <i class="fas fa-bed"></i>
                                </div>
                                <ul class="tien-ich">
                                    <li class="tien-ich-item">28 m2</li>
                                    <li class="tien-ich-item">Điều hòa</li>
                                    <li class="tien-ich-item">TV</li>
                                    <li class="tien-ich-item">Tủ lạnh</li>
                                    <li class="tien-ich-item">Ban công</li>
                                </ul>
                            </div>
                            <div class="phong__dat">
                                <div>                                
                                    <p class="gia-mota">Giá phòng</p>
                                    <h1 class="gia">450,000 VND</h1>
                                </div>
                                <select name="so-luong-phong" class="so-luong-phong">
                                    <option value="">Chọn số lượng phòng</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                                <button class="dat-phong-btn dat-btn">Đặt phòng</button>
                            </div>
                        </div>

                    </div>
                    <div class="col l-6 m-12 c-12">
                        <div class="phong-item">
                            <h1 class="phong__loai">Phòng Deluxe</h1>
                            <div class="phong__chitiet">
                                <div class="so-nguoi">
                                    <span>Phù hợp cho: </span>
                                    <i class="fas fa-user"></i>
                                    <i class="fas fa-user"></i>
                                </div>
                                <div class="giuong">
                                    <span>Giường đôi </span>
                                    <i class="fas fa-bed"></i>
                                </div>
                                <ul class="tien-ich">
                                    <li class="tien-ich-item">28 m2</li>
                                    <li class="tien-ich-item">Điều hòa</li>
                                    <li class="tien-ich-item">TV</li>
                                    <li class="tien-ich-item">Tủ lạnh</li>
                                    <li class="tien-ich-item">Ban công</li>
                                </ul>
                            </div>
                            <div class="phong__dat">
                                <div>                                
                                    <p class="gia-mota">Giá phòng</p>
                                    <h1 class="gia">450,000 VND</h1>
                                </div>
                                <select name="so-luong-phong" class="so-luong-phong">
                                    <option value="">Chọn số lượng phòng</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                </select>
                                <button class="dat-phong-btn dat-btn">Đặt phòng</button>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="chi-tiet-phong-wrapper">
                    <div class="overlay phong">

                    </div>
                </div>
            </div>

        </div>

        <footer class="footer__bottom">
            <div class="grid wide"></div>
            <div class="row">
                <div class="col l-12">
                    <p>© 2022 Lập trình Website đặt phòng khách sạn</p>                        
                </div>
            </div>
        </footer>
        <script src="assets/js/base.js" type="module"></script>
        <script src="assets/js/chiTietHotel.js" type="module"></script>
        <script src="assets/js/lich.js"></script>
    </body>
</html>
