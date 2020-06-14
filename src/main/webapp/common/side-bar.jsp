<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="wrapper">
        <!-- Sidebar  -->
        <nav id="sidebar">
            <div class="sidebar-header">
                <h3>holiShirts</h3>
                <strong>CS</strong>
            </div>

            <ul class="list-unstyled components">
                <li class="active">
                   
                <li>
                    <a href="<c:url value="/" />">
                        <i class="fas fa-home"></i>
                        Home
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/catalog" />">
                        <i class="fas fa-store"></i>
                        Catálogo
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/cart"/>">
                        <i class="fas fa-shopping-cart"></i>
                        Carrinho
                    </a>
                </li>
            </ul>
        </nav>
    </div>