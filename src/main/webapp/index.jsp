
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="assets/fonts/fontawesome-free-5.15.4-web/css/all.min.css">
        <link rel="stylesheet" href="assets/css/grid.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <link rel="stylesheet" href="assets/css/base.css">
        <link rel="stylesheet" href="assets/css/responsive.css">
        <link rel="stylesheet" href="register.html">
        <link rel="stylesheet" href="login.html">
        <link rel="stylesheet" href="assets/css/lich.css">
        <title>Đặt phòng nhanh chóng</title>

    </head>
    <body>
        <div class="main">
            <header class="header">
                <div class="grid wide">
                    <div class="row">
                        <div class="col l-6 m-5 c-5 logo-and-search">                        
                            <a href='<c:url value="/trang-chu"/>' class="logo"><h1>Booking Room</h1></a>

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
                                    <li class="navbar__menu-item"><a href="register.html">Đăng ký</a></li>
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
                                        <svg version="1.1" viewBox="0 0 244 50" class="svg-icon svg-fill" style="width: 150px;">
                                        <path
                                            pid="0"
                                            d="M25.093 0c13.781.06 24.94 11.317 24.882 25.106C49.917 38.894 38.663 50.058 24.88 50 11.1 49.942-.059 38.683.001 24.894.057 11.106 11.31-.058 25.092 0zm11.801 31.9L14.398 16.053c.241-.26.48-.518.74-.777 2.7-2.687 5.971-4.031 9.775-4.015 3.804.015 7.064 1.388 9.741 4.098.238.241.476.482.694.743l-5.951 4.133 2.381 1.688 5.153-3.576v.02L39.33 16.7c-.692-1.203-1.584-2.325-2.616-3.39-3.231-3.292-7.167-4.947-11.788-4.967-4.6-.019-8.53 1.603-11.809 4.867a17.806 17.806 0 00-2.682 3.408l1.429 1.004 23.429 16.51c-.24.26-.48.518-.74.777-2.7 2.687-5.971 4.011-9.775 3.996-3.803-.016-7.063-1.37-9.74-4.08-.258-.26-.496-.521-.734-.782l6.111-4.251-.139-.181-2.183-1.528-5.373 3.735v-.021l-2.377 1.65a17.824 17.824 0 002.654 3.43c3.253 3.291 7.169 4.946 11.769 4.965 4.62.021 8.549-1.602 11.83-4.866 1.06-1.075 1.96-2.21 2.682-3.406l-2.383-1.67zm45.839 2.652l-12.45-.05.096-21.789a.957.957 0 00-.965-.945l-.678-.003a.96.96 0 00-.974.939v.047c-.048.095-.05.236-.05.33l-.098 22.636a1.337 1.337 0 001.351 1.326l13.805.056a.958.958 0 00.973-.938l.003-.662a1.037 1.037 0 00-1.013-.946v-.001zm27.64-22.67l-.871-.003c-.563-.003-1.027.42-1.027.937l-.075 15.878c-.014 3.157-3.252 5.735-7.197 5.72-3.943-.014-7.158-2.618-7.144-5.775l.123-15.5.052-.282a.957.957 0 00-.201-.756 1.128 1.128 0 00-.767-.332l-.87-.004c-.513-.002-.924.326-1.028.75a.857.857 0 00-.156.47l-.073 15.69c-.02 4.616 4.47 8.402 9.95 8.424 5.48.022 10.004-3.73 10.025-8.3l.122-15.5.054-.282a.952.952 0 00-.203-.755c-.152-.189-.458-.379-.714-.38zm48.638 11.226c-3.275-1.249-6.696-2.496-6.688-4.393.01-2.42 2.721-4.401 6.05-4.387 3.331.014 6.025 2.017 6.014 4.436l-.002.38a.96.96 0 00.961.953l.675.003a.96.96 0 00.971-.945v-.38c.009-1.66-.709-3.276-2.006-4.515-1.635-1.618-4.092-2.532-6.65-2.494-4.873-.02-8.698 3.047-8.715 6.938.03 3.748 4.416 5.38 8.656 6.962 3.324 1.247 6.795 2.495 6.785 4.487-.01 2.372-3.155 4.399-6.775 4.385-3.668-.016-6.747-2.07-6.737-4.44l.001-.38a.961.961 0 00-.96-.954l-.676-.002a.96.96 0 00-.97.945v.38c-.018 3.843 4.215 6.991 9.38 7.012 5.163.022 9.423-3.092 9.44-6.934-.033-3.796-4.464-5.427-8.754-7.057zM193.66 11.84l-18.844-.076c-.547-.003-.996.42-.999.94l-.002.66c-.002.52.443.946.99.949l8.103.032-.095 21.802c-.003.52.443.947.99.949l.696.002c.546.003.996-.42 1-.94l.095-21.802 8.055.031c.547.003.996-.42.999-.94l.003-.66c.003-.52-.443-.946-.99-.947zm49.264.515c-.144-.33-.481-.52-.867-.52l-.87-.005a.963.963 0 00-.677.28l-.145.14-.147.142-6.609 9.677-6.72-9.734c-.048-.046-.096-.094-.096-.14l-.143-.143a.97.97 0 00-.675-.285l-.774-.003a1.021 1.021 0 00-.872.515 1.03 1.03 0 00.045.988l7.966 11.576-.049 11.307c.006.527.437.95.964.946l.675.002a.957.957 0 00.97-.937l.049-11.308 7.923-11.509c.194-.329.243-.705.052-.989zm-37.898 12.047l3.75-7.994 3.728 8.023-7.478-.03zm15.615 10.954v-.047l-10.5-22.733c-.15-.425-.549-.71-1.102-.807-.603-.049-1.156.231-1.41.703v.046l-10.705 22.697v.047l-.204.471c-.095.283-.06.595.097.849.188.258.483.416.802.428l.854.004c.453.001.806-.282.958-.657l.05-.141 4.314-9.277 9.95.04 4.28 9.31.049.14c.15.38.5.664.952.667l.854.002c.352.002.654-.139.806-.422.152-.281.254-.563.104-.847l-.149-.473zm-86.008-15.15l.817.003a.963.963 0 00.729-.347l4.755-6.218.412-.478a.763.763 0 00.095-.915c-.135-.306-.453-.482-.816-.483l-.863-.004a.929.929 0 00-.868.608l-4.983 6.522a.94.94 0 00-.096.915c.135.22.453.394.816.397h.002zm-6.998 8.45l-.819-.002a.96.96 0 00-.728.347l-4.755 6.207-.413.48a.77.77 0 00-.094.92c.135.309.452.485.816.488l.864.002a.91.91 0 00.82-.478l5.03-6.601a.949.949 0 00.095-.92 1.027 1.027 0 00-.816-.443zm8.019-.453l.148.14 5.55 7.248c.248.284.246.662.095.99-.15.332-.5.519-.9.517l-.896-.004a1.013 1.013 0 01-.697-.286l-.149-.14c-.03-.002-.042-.02-.059-.044a.212.212 0 00-.04-.052l-5.501-7.151a1.005 1.005 0 01-.15-.237l-11.694-15.39-.446-.521c-.248-.285-.246-.662-.096-.993.15-.33.501-.518.9-.516l.897.004a.99.99 0 01.845.428l.15.19 11.992 15.721s.05.048.05.096z"
                                            ></path>
                                        </svg>
                                        <label for="menu-checkbox" class="mobile-menu__close">
                                            <i class="fas fa-times"></i>
                                        </label>                                    
                                    </div>
                                    <ul class="mobile-menu__list">
                                        <li class="navbar__menu-item mobile-menu__item"><a class="mobile-menu__item-link" href="">Trang chủ</a></li>                                    
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
                        <div class="col l-12 m-12 c-12">
                            <!-- <img src="" alt="" class="slide-img"> slide -->
                        </div>                
                    </div>
                    <div class="row">
                        <div class="col l-12 m-12 c-12 welcome">
                            <h1>Chào mừng đến với Website đặt phòng!</h1>
                            <p>Đặt phòng khách sạn với Website đặt phòng nhanh chóng tiện lợi.</p>
                            <p>
                                <a href="/dang-nhap?action=login">Đăng nhập</a>
                                hoặc 
                                <a href="register.jsp">Đăng ký</a>
                                để trải nghiệm!
                            </p>
                        </div>
                    </div>
                    <div class = "product">
                        <div class="row">
                            <div class="col l-12 m-12 c-12">
                                <div class="product__header">
                                    <h1>Địa điểm nổi bật</h1>
                                    <p>Băt đầu chuyến hành trình chinh phục thế giới của bạn</p>
                                </div>
                            </div>
                        </div>
                        <div class="slide-list group-1">
                            
                        </div>
                    </div>

                    <div class = "product">                    
                        <div class="row">
                            <div class="col l-12 m-12 c-12">
                                <div class="product__header">
                                    <h1>Gợi ý từ chúng tôi</h1>
                                    <p>Những địa điểm thường đến mà chúng tôi gợi ý dành cho bạn</p>
                                </div>
                            </div>
                        </div>
                        <div class="slide-list group-2">
                            
                        </div>
                    </div>

                    <div class = "product">                    
                        <div class="row">
                            <div class="col l-12 m-12 c-12">
                                <div class="product__header">
                                    <h1>Gợi ý khám phá</h1>
                                    <p>Để mỗi chuyến đi là một hành trình truyền cảm hứng, mỗi căn phòng là một khoảng trời an yên</p>
                                </div>
                            </div>
                        </div>
                        <div class="slide-list group-3">
                           
                        </div>
                    </div>


                </div>
            </div>

            <footer class="footer">
                <div class="footer__top">
                    <div class="grid wide">                
                        <div class="row footer-margin">
                            <div class="col l-3 m-6">
                                <div class="row">  
                                    <div class="col l-12 m-12 c-12 about-item">                                                            
                                        <svg version="1.1" viewBox="0 0 40 40" class="svg-icon svg-fill" style="width: 40px; height: 40px;">
                                        <defs>
                                        <linearGradient x1="50%" y1="0%" x2="50%" y2="100%" id="svgicon_whatsapp_b">
                                        <stop stop-color="#4B4B4B" offset="0%"></stop>
                                        <stop offset="100%"></stop>
                                        </linearGradient>
                                        <linearGradient x1="0%" y1="25.77%" x2="100%" y2="74.23%" id="svgicon_whatsapp_e">
                                        <stop stop-color="#C77D0E" offset="0%"></stop>
                                        <stop stop-color="#F98713" offset="100%"></stop>
                                        </linearGradient>
                                        <linearGradient x1="0%" y1="24.995%" x2="100%" y2="75.005%" id="svgicon_whatsapp_f">
                                        <stop stop-color="#F98713" offset="0%"></stop>
                                        <stop stop-color="#FBAC36" offset="100%"></stop>
                                        </linearGradient>
                                        <path pid="0" d="M9.083 17.431L3.52 20.16H1.76l1.4-5.483C1.182 13.132 0 11.172 0 9.04 0 4.047 6.483 0 14.48 0c7.997 0 14.48 4.047 14.48 9.04 0 4.993-6.483 9.04-14.48 9.04-1.908 0-3.73-.23-5.397-.649z" id="svgicon_whatsapp_a"></path>
                                        <path pid="1" d="M9.083 17.431L3.52 20.16H1.76l1.4-5.483C1.182 13.132 0 11.172 0 9.04 0 4.047 6.483 0 14.48 0c7.997 0 14.48 4.047 14.48 9.04 0 4.993-6.483 9.04-14.48 9.04-1.908 0-3.73-.23-5.397-.649z" id="svgicon_whatsapp_d"></path>
                                        </defs>
                                        <g fill="none" fill-rule="evenodd">
                                        <circle pid="2" fill-opacity=".03" fill="#000" cx="20" cy="20" r="20"></circle>
                                        <ellipse pid="3" fill="#E4E4E4" cx="20" cy="36.64" rx="20" ry="1.28"></ellipse>
                                        <g transform="translate(0 4)">
                                        <mask id="svgicon_whatsapp_c" fill="#fff"><use xlink:href="#svgicon_whatsapp_a"></use></mask>
                                        <use fill="url(#svgicon_whatsapp_b)" xlink:href="#svgicon_whatsapp_a"></use>
                                        <path
                                            pid="4"
                                            d="M10.843 17.431L5.28 20.16H3.52l1.4-5.483c-1.978-1.545-3.16-3.505-3.16-5.637C1.76 4.047 8.243 0 16.24 0c7.997 0 14.48 4.047 14.48 9.04 0 4.993-6.483 9.04-14.48 9.04-1.908 0-3.73-.23-5.397-.649z"
                                            fill="#414141"
                                            mask="url(#svgicon_whatsapp_c)"
                                            ></path>
                                        <circle pid="5" fill="#FFF" opacity=".2" mask="url(#svgicon_whatsapp_c)" cx="9.6" cy="8.48" r="1.6"></circle>
                                        <circle pid="6" fill="#FFF" opacity=".2" mask="url(#svgicon_whatsapp_c)" cx="14.72" cy="8.48" r="1.6"></circle>
                                        <circle pid="7" fill="#FFF" opacity=".2" mask="url(#svgicon_whatsapp_c)" cx="19.84" cy="8.48" r="1.6"></circle>
                                        </g>
                                        <g transform="matrix(-1 0 0 1 40 16)">
                                        <mask id="svgicon_whatsapp_g" fill="#fff"><use xlink:href="#svgicon_whatsapp_d"></use></mask>
                                        <use fill="url(#svgicon_whatsapp_e)" xlink:href="#svgicon_whatsapp_d"></use>
                                        <path
                                            pid="8"
                                            d="M7.808 17.112L1.6 20.16H-.16l1.759-6.03C-.21 12.622-1.28 10.75-1.28 8.72c0-4.993 6.483-9.04 14.48-9.04 7.997 0 14.48 4.047 14.48 9.04 0 4.993-6.483 9.04-14.48 9.04-1.906 0-3.725-.23-5.392-.648z"
                                            fill="url(#svgicon_whatsapp_f)"
                                            mask="url(#svgicon_whatsapp_g)"
                                            ></path>
                                        <circle pid="9" fill="#CF3B00" opacity=".413" mask="url(#svgicon_whatsapp_g)" cx="8.64" cy="8.48" r="1.6"></circle>
                                        <circle pid="10" fill="#CF3B00" opacity=".413" mask="url(#svgicon_whatsapp_g)" cx="13.76" cy="8.48" r="1.6"></circle>
                                        <circle pid="11" fill="#CF3B00" opacity=".413" mask="url(#svgicon_whatsapp_g)" cx="18.88" cy="8.48" r="1.6"></circle>
                                        </g>
                                        </g>
                                        </svg>
                                        <div class="about-item__content">
                                            <p>Messenger</p>
                                            <a href="">http://abc.xyz</a>
                                        </div>
                                    </div>
                                    <div class="col l-12 m-12 c-12 about-item">
                                        <svg version="1.1" viewBox="0 0 40 40" class="svg-icon svg-fill" style="width: 40px; height: 40px;">
                                        <defs>
                                        <linearGradient x1="0%" y1="25.77%" x2="100%" y2="74.23%" id="svgicon_call-center_b">
                                        <stop stop-color="#C77D0E" offset="0%"></stop>
                                        <stop stop-color="#F98713" offset="100%"></stop>
                                        </linearGradient>
                                        <linearGradient x1="0%" y1="25.77%" x2="100%" y2="74.23%" id="svgicon_call-center_c">
                                        <stop stop-color="#F98713" offset="0%"></stop>
                                        <stop stop-color="#FBAC36" offset="100%"></stop>
                                        </linearGradient>
                                        <linearGradient x1="50%" y1="0%" x2="50%" y2="100%" id="svgicon_call-center_f">
                                        <stop stop-color="#4B4B4B" offset="0%"></stop>
                                        <stop offset="100%"></stop>
                                        </linearGradient>
                                        <path
                                            pid="0"
                                            d="M6.83 13.108L2.647 15.16H1.323l1.053-4.123C.89 9.875 0 8.401 0 6.797 0 3.045 4.875 0 10.889 0c6.013 0 10.888 3.044 10.888 6.798s-4.875 6.798-10.888 6.798c-1.435 0-2.805-.173-4.059-.488z"
                                            id="svgicon_call-center_a"
                                            ></path>
                                        <path
                                            pid="1"
                                            d="M0 6.084c0-3 2.176-5.168 3.71-5.832C5.241-.41 9.103.39 9.53.866c.427.477 1.048 5.218.852 6.143-.11.525-.774 1.077-1.988 1.656a1 1 0 00-.55 1.097 12.858 12.858 0 001.443 3.897c.813 1.424 1.954 2.861 3.422 4.31l.632-.587a4.094 4.094 0 012.789-1.097c1.037 0 2.04.378 2.819 1.064l3.157 2.778a1 1 0 01.282 1.085c-.49 1.386-1.315 2.368-2.473 2.947-1.333.667-3.068.937-5.206.81-3 0-8.105-3.52-11-8.217C.814 12.054 0 9.084 0 6.084z"
                                            id="svgicon_call-center_e"
                                            ></path>
                                        </defs>
                                        <g fill="none" fill-rule="evenodd">
                                        <circle pid="2" fill-opacity=".03" fill="#000" cx="20" cy="20" r="20"></circle>
                                        <ellipse pid="3" fill="#E4E4E4" cx="20" cy="36.64" rx="20" ry="1.28"></ellipse>
                                        <g transform="translate(15.71 7)">
                                        <mask id="svgicon_call-center_d" fill="#fff"><use xlink:href="#svgicon_call-center_a"></use></mask>
                                        <use fill="url(#svgicon_call-center_b)" xlink:href="#svgicon_call-center_a"></use>
                                        <path
                                            pid="4"
                                            d="M8.154 13.108L3.97 15.16H2.647L3.7 11.037c-1.487-1.162-2.377-2.636-2.377-4.24C1.323 3.045 6.2 0 12.213 0 18.225 0 23.1 3.044 23.1 6.798s-4.875 6.798-10.889 6.798c-1.434 0-2.804-.173-4.058-.488z"
                                            fill="url(#svgicon_call-center_c)"
                                            mask="url(#svgicon_call-center_d)"
                                            ></path>
                                        <circle pid="5" fill="#CF3B00" opacity=".413" mask="url(#svgicon_call-center_d)" cx="7.219" cy="6.377" r="1.203"></circle>
                                        <circle pid="6" fill="#CF3B00" opacity=".413" mask="url(#svgicon_call-center_d)" cx="11.069" cy="6.377" r="1.203"></circle>
                                        <circle pid="7" fill="#CF3B00" opacity=".413" mask="url(#svgicon_call-center_d)" cx="14.919" cy="6.377" r="1.203"></circle>
                                        </g>
                                        <g transform="translate(2 11.03)">
                                        <mask id="svgicon_call-center_g" fill="#fff"><use xlink:href="#svgicon_call-center_e"></use></mask>
                                        <use fill="url(#svgicon_call-center_f)" xlink:href="#svgicon_call-center_e"></use>
                                        <path
                                            pid="8"
                                            d="M2.71 6.084c0-3 2.175-5.168 3.708-5.832C7.951-.41 11.813.39 12.24.866c.426.477 1.047 5.218.852 6.143-.112.525-.775 1.077-1.99 1.656a1 1 0 00-.55 1.097 12.858 12.858 0 001.444 3.897c.813 1.424 1.954 2.861 3.422 4.31l.632-.587a4.094 4.094 0 012.789-1.097c1.038 0 2.04.378 2.819 1.064l3.157 2.778a1 1 0 01.282 1.085c-.49 1.386-1.315 2.368-2.472 2.947-1.334.667-3.07.937-5.207.81-3 0-8.105-3.52-11-8.217C3.523 12.054 2.71 9.084 2.71 6.084z"
                                            fill="#414141"
                                            mask="url(#svgicon_call-center_g)"
                                            ></path>
                                        </g>
                                        </g>
                                        </svg>
                                        <div class="about-item__content">
                                            <p>Call center</p>
                                            <a href="tel:">180000000</a>                                      
                                        </div>                              
                                    </div>
                                </div>
                            </div>
                            <div class="col l-3 m-6 mb-invisible widget">
                                <h2 class="widget__title">TOP KHÁCH SẠN ĐƯỢC YÊU THÍCH</h2>
                                <ul class="widget__menu">
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Đà Lạt</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Hà Nội</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Hồ Chí Minh</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Sapa</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Vũng Tàu</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Tam Đảo</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Hội An</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Đà Nẵng</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Hạ Long</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Khách sạn Phan Thiết</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="col l-3 m-6 mb-invisible widget">
                                <h2 class="widget__title">Không gian ưa thích</h2>
                                <ul class="widget__menu">
                                    <li class="widget__menu-item">
                                        <a href="">Căn hộ dịch vụ</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Biệt thự</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Nhà riêng</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Studio</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Tranvel Guide</a>
                                    </li>                            
                                </ul>
                            </div>

                            <div class="col l-3 m-6 mb-invisible widget">                        
                                <h2 class="widget__title">Về chúng tôi</h2>
                                <ul class="widget__menu">
                                    <li class="widget__menu-item">
                                        <a href="">Blog</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Điều khoản hoạt động</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">1800 1234</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">+84 1234 5678</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">info@ABC.COM</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Trang thông tin dành cho chủ nhà</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Cơ hội nghề nghiệp</a>
                                    </li>
                                    <li class="widget__menu-item">
                                        <a href="">Tạp chí du lịch</a>
                                    </li>                                
                                </ul>    
                            </div>       

                        </div>

                       
                    </div>
                </div>

                <div class="footer__bottom">
                    <div class="grid wide"></div>
                    <div class="row">
                        <div class="col l-12">
                            <p>© 2022 Lập trình Website đặt phòng khách sạn</p>                        
                        </div>
                    </div>
                </div>
            </footer>

            <script src="assets/js/base.js" type="module"></script>
            <script src="assets/js/index.js" type="module"></script>

        </div>

</html>
