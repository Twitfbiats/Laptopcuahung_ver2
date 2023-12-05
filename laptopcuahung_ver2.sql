-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 05, 2023 lúc 10:53 AM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `laptopcuahung_ver2`
--
CREATE DATABASE IF NOT EXISTS `laptopcuahung_ver2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `laptopcuahung_ver2`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `battery`
--

CREATE TABLE `battery` (
  `id` bigint(20) NOT NULL,
  `estimated_working_time` tinyint(4) NOT NULL,
  `model` varchar(50) DEFAULT NULL,
  `voltages` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `battery`
--

INSERT INTO `battery` (`id`, `estimated_working_time`, `model`, `voltages`) VALUES
(1, 0, 'Lithium-ion 4 Cell', 5.75),
(2, 0, 'Lithium-ion 3 Cell', 20),
(3, 0, NULL, 15),
(4, 0, NULL, 23),
(5, 0, 'Lithium polymer', 12),
(6, 0, 'Lithium-ion', 18);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `id` bigint(20) NOT NULL,
  `note` varchar(1000) DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  `pay_with` varchar(255) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `total` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `purchaser_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`id`) VALUES
(1),
(2),
(3),
(4),
(5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart_info`
--

CREATE TABLE `cart_info` (
  `cart_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cart_info`
--

INSERT INTO `cart_info` (`cart_id`, `product_id`, `quantity`) VALUES
(2, 4, 0),
(2, 5, 0),
(2, 6, 0),
(3, 5, 0),
(3, 6, 0),
(4, 5, 0),
(4, 6, 0),
(5, 1, 11),
(5, 2, 4),
(5, 3, 3),
(5, 4, 11),
(5, 5, 6),
(5, 6, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'laptop');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `contact`
--

CREATE TABLE `contact` (
  `id` bigint(20) NOT NULL,
  `contact_content` varchar(255) DEFAULT NULL,
  `contact_date` datetime DEFAULT NULL,
  `contact_email` varchar(30) DEFAULT NULL,
  `response_content` varchar(255) DEFAULT NULL,
  `response_date` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cpu`
--

CREATE TABLE `cpu` (
  `id` bigint(20) NOT NULL,
  `additional_info` varchar(255) DEFAULT NULL,
  `core` smallint(6) NOT NULL,
  `max_processor_speed` float NOT NULL,
  `model` varchar(50) DEFAULT NULL,
  `processor_speed` float NOT NULL,
  `thread` smallint(6) NOT NULL,
  `manufacturer` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `cpu`
--

INSERT INTO `cpu` (`id`, `additional_info`, `core`, `max_processor_speed`, `model`, `processor_speed`, `thread`, `manufacturer`) VALUES
(1, 'Cache 12 MB', 6, 4.5, 'Core i5-11400H', 2.7, 12, NULL),
(2, 'Cache 8 MB', 4, 4.5, 'Core i5-10300H', 2.5, 8, NULL),
(3, 'Cache 12 MB', 12, 4.5, 'Core i5-12500H', 3.3, 16, NULL),
(4, NULL, 6, 4.2, 'AMD Ryzen 5-5600H', 3.3, 12, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `display`
--

CREATE TABLE `display` (
  `id` bigint(20) NOT NULL,
  `display_type` varchar(10) DEFAULT NULL,
  `panel_type` varchar(15) DEFAULT NULL,
  `refresh_rate` smallint(6) NOT NULL,
  `resolution` varchar(11) DEFAULT NULL,
  `size` float NOT NULL,
  `technology` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `display`
--

INSERT INTO `display` (`id`, `display_type`, `panel_type`, `refresh_rate`, `resolution`, `size`, `technology`) VALUES
(1, 'LED', 'IPS', 144, '1920x1080', 15.6, 'Acer ComfyView LED-backlit'),
(2, 'LED', 'IPS', 144, '1920x1080', 15.6, 'Anti-glare LED-backlit'),
(3, 'LED', 'IPS', 165, '2560x1440', 15.6, 'IPS LCD LED Backlit, True Tone'),
(4, 'LED', 'IPS', 144, '1920x1080', 15.6, 'FHD 250 nits 45% NTSC'),
(5, 'LED', 'IPS', 144, '1920x1080', 15.6, 'IPS FHD 358 nits 45% NTSC');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `drive`
--

CREATE TABLE `drive` (
  `id` bigint(20) NOT NULL,
  `drive_type` varchar(10) DEFAULT NULL,
  `max_read` smallint(6) NOT NULL,
  `max_write` smallint(6) NOT NULL,
  `model` varchar(50) DEFAULT NULL,
  `size` smallint(6) NOT NULL,
  `manufacturer` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `drive`
--

INSERT INTO `drive` (`id`, `drive_type`, `max_read`, `max_write`, `model`, `size`, `manufacturer`) VALUES
(1, 'SSD', 0, 0, NULL, 512, NULL),
(2, 'SSD', 0, 0, NULL, 512, NULL),
(3, 'M2', 0, 0, NULL, 512, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `graphic_card`
--

CREATE TABLE `graphic_card` (
  `id` bigint(20) NOT NULL,
  `graphic_card_type` varchar(15) NOT NULL,
  `memory` smallint(6) NOT NULL,
  `model` varchar(50) DEFAULT NULL,
  `manufacturer` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `graphic_card`
--

INSERT INTO `graphic_card` (`id`, `graphic_card_type`, `memory`, `model`, `manufacturer`) VALUES
(1, 'Integrated', 0, 'Intel UHD Graphics', NULL),
(2, 'Dedicated', 6, 'NVIDIA GeForce RTX 3060', NULL),
(3, 'Dedicated', 4, 'NVIDIA GeForce GTX 1650', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `laptop`
--

CREATE TABLE `laptop` (
  `id` bigint(20) NOT NULL,
  `color` varchar(15) DEFAULT NULL,
  `drive_slot` tinyint(4) NOT NULL,
  `material` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `port_info` varchar(100) DEFAULT NULL,
  `ram_slot` tinyint(4) NOT NULL,
  `size` varchar(20) DEFAULT NULL,
  `weight` float NOT NULL,
  `battery` bigint(20) DEFAULT NULL,
  `display` bigint(20) DEFAULT NULL,
  `manufacturer` bigint(20) DEFAULT NULL,
  `operating_system` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `laptop`
--

INSERT INTO `laptop` (`id`, `color`, `drive_slot`, `material`, `name`, `port_info`, `ram_slot`, `size`, `weight`, `battery`, `display`, `manufacturer`, `operating_system`) VALUES
(1, 'Black', 2, 'Plastic', 'Acer Nitro Gaming AN515-57-5669 i5 11400H', '1 DC-in jack\n1 HDMI\n1 Jack 3.5 mm\n1 LAN\n3 USB 3.0', 2, '363.4x255x23.9', 2.2, 1, 1, 1, 1),
(2, 'Black', 2, 'Plastic', 'Asus TUF Gaming FX506LHB-HN188W i5 10300H', '1 HDMI\n1 Jack 3.5 mm\n1 Type C\n1 USB 2.0\n2 USB 3.0', 2, '359x256x24.9', 2.3, 2, 2, 2, 1),
(3, 'Black', 2, 'Plastic', 'Gigabyte Gaming G5 GD i5 11400H', '1 DC-in jack\n1 HDMI 2.0\n1 Jack 3.5 mm\n1 LAN\n1 SD Card\n1 USB 2.0\n1 USB 3.2 Gen 1\n2 USB 3.2 Gen 2\n', 2, '361x258x24.9', 2.2, 3, 2, 3, 1),
(4, 'Black', 2, 'Plastic', 'Lenovo Gaming Legion 5 15IAH7H i5-12500H', '1 HDMI\n1 Jack 3.5 mm\n1 LAN\n1 Thunderbolt\n2 Type C\n3 USB 3.2\n', 2, '358.8x262.35x19.99', 2.35, 4, 3, 4, 1),
(5, 'Black', 2, 'Plastic', 'MSI Gaming GF63 Thin 11SC-664VN i5-11400H', '1 HDMI\n1 LAN\n1 Type C\n3 USB 3.2 Gen 1\n', 2, '359x254x21.7', 1.86, 5, 4, 5, 1),
(6, 'Black', 2, 'Plastic', 'MSI Gaming Alpha 15 B5EEK-203VN R5 5600H', '1 HDMI 2.1\n1 Jack 3.5 mm\n1 LAN\n1 Type C\n1 USB 2.0\n2 USB 3.2 Gen 1\n', 2, '359x259x23.95', 2.35, 6, 5, 5, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `laptop_cpu`
--

CREATE TABLE `laptop_cpu` (
  `laptop` bigint(20) NOT NULL,
  `cpu` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `laptop_cpu`
--

INSERT INTO `laptop_cpu` (`laptop`, `cpu`) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 3),
(5, 1),
(6, 4);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `laptop_drive`
--

CREATE TABLE `laptop_drive` (
  `laptop` bigint(20) NOT NULL,
  `drive` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `laptop_drive`
--

INSERT INTO `laptop_drive` (`laptop`, `drive`) VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 1),
(5, 1),
(6, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `laptop_graphic_card`
--

CREATE TABLE `laptop_graphic_card` (
  `laptop` bigint(20) NOT NULL,
  `graphic_card` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `laptop_graphic_card`
--

INSERT INTO `laptop_graphic_card` (`laptop`, `graphic_card`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 3),
(6, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `laptop_ram`
--

CREATE TABLE `laptop_ram` (
  `laptop` bigint(20) NOT NULL,
  `ram` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `laptop_ram`
--

INSERT INTO `laptop_ram` (`laptop`, `ram`) VALUES
(1, 1),
(2, 2),
(3, 1),
(3, 1),
(4, 3),
(4, 3),
(5, 1),
(6, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `manufacturer`
--

CREATE TABLE `manufacturer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `manufacturer`
--

INSERT INTO `manufacturer` (`id`, `name`) VALUES
(1, 'Acer'),
(2, 'Asus'),
(3, 'Gigabyte'),
(4, 'Lenovo'),
(5, 'MSI');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `operating_system`
--

CREATE TABLE `operating_system` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `type` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `operating_system`
--

INSERT INTO `operating_system` (`id`, `name`, `type`) VALUES
(1, 'Windows 11', 64);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cost` double NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `receive_date` datetime DEFAULT NULL,
  `receive_phone_number` varchar(15) DEFAULT NULL,
  `receiver_email` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(50) DEFAULT NULL,
  `ship_date` datetime DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `total` double NOT NULL,
  `shipper_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_discount`
--

CREATE TABLE `order_discount` (
  `id` bigint(20) NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `discount_unit` varchar(255) DEFAULT NULL,
  `discount_value` double NOT NULL,
  `valid_until` datetime DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order_info`
--

CREATE TABLE `order_info` (
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `embedded3dmodel` varchar(10000) DEFAULT NULL,
  `intro` text DEFAULT NULL,
  `price` double NOT NULL,
  `sold` int(11) NOT NULL,
  `stock` smallint(6) NOT NULL,
  `warranty` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `cpu` bigint(20) DEFAULT NULL,
  `drive` bigint(20) DEFAULT NULL,
  `graphic_card` bigint(20) DEFAULT NULL,
  `laptop` bigint(20) DEFAULT NULL,
  `ram` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `embedded3dmodel`, `intro`, `price`, `sold`, `stock`, `warranty`, `category_id`, `cpu`, `drive`, `graphic_card`, `laptop`, `ram`) VALUES
(1, '<div class=\\\"sketchfab-embed-wrapper\\\"> <iframe title=\\\"Acer Nitro 5 Laptop\\\" frameborder=\\\"0\\\" allowfullscreen mozallowfullscreen=\\\"true\\\" webkitallowfullscreen=\\\"true\\\" allow=\\\"autoplay; fullscreen; xr-spatial-tracking\\\" xr-spatial-tracking execution-while-out-of-viewport execution-while-not-rendered web-share width=\\\"640\\\" height=\\\"320\\\" src=\\\"https://sketchfab.com/models/1158dcc7239645209ac7262533410137/embed?autostart=1\\\"> </iframe> <p style=\\\"font-size: 13px; font-weight: normal; margin: 5px; color: #4A4A4A;\\\"> <a href=\\\"https://sketchfab.com/3d-models/acer-nitro-5-laptop-1158dcc7239645209ac7262533410137?utm_medium=embed&utm_campaign=share-popup&utm_content=1158dcc7239645209ac7262533410137\\\" target=\\\"_blank\\\" rel=\\\"nofollow\\\" style=\\\"font-weight: bold; color: #1CAAD9;\\\"> Acer Nitro 5 Laptop </a> by <a href=\\\"https://sketchfab.com/fajarzh?utm_medium=embed&utm_campaign=share-popup&utm_content=1158dcc7239645209ac7262533410137\\\" target=\\\"_blank\\\" rel=\\\"nofollow\\\" style=\\\"font-weight: bold; color: #1CAAD9;\\\"> Fajar Zuhri Hadiyanto </a> on <a href=\\\"https://sketchfab.com?utm_medium=embed&utm_campaign=share-popup&utm_content=1158dcc7239645209ac7262533410137\\\" target=\\\"_blank\\\" rel=\\\"nofollow\\\" style=\\\"font-weight: bold; color: #1CAAD9;\\\">Sketchfab</a></p></div>', 'Introducing the Acer Nitro 5 - a powerhouse laptop designed for gaming and productivity. With its high-performance processor, stunning graphics, and advanced cooling technology, the Nitro 5 delivers smooth gameplay and seamless multitasking. Upgrade your gaming experience today with the Acer Nitro 5.', 17990000, 0, 1000, '1 years', 1, NULL, NULL, NULL, 1, NULL),
(2, '<div class=\"sketchfab-embed-wrapper\"> <iframe title=\"ASUS TUF A15 - Updated with stickers\" frameborder=\"0\" allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" allow=\"autoplay; fullscreen; xr-spatial-tracking\" xr-spatial-tracking execution-while-out-of-viewport execution-while-not-rendered web-share width=\"640\" height=\"320\" src=\"https://sketchfab.com/models/fabec192a52d4e7ba93826f7a84320f3/embed?autostart=1\"> </iframe> <p style=\"font-size: 13px; font-weight: normal; margin: 5px; color: #4A4A4A;\"> <a href=\"https://sketchfab.com/3d-models/asus-tuf-a15-updated-with-stickers-fabec192a52d4e7ba93826f7a84320f3?utm_medium=embed&utm_campaign=share-popup&utm_content=fabec192a52d4e7ba93826f7a84320f3\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> ASUS TUF A15 - Updated with stickers </a> by <a href=\"https://sketchfab.com/just_banana3?utm_medium=embed&utm_campaign=share-popup&utm_content=fabec192a52d4e7ba93826f7a84320f3\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> just_banana3 </a> on <a href=\"https://sketchfab.com?utm_medium=embed&utm_campaign=share-popup&utm_content=fabec192a52d4e7ba93826f7a84320f3\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\">Sketchfab</a></p></div>', 'Introducing the Asus TUF Gaming FX506LHB laptop, where power meets performance in a sleek and rugged design. Packed with a formidable Intel processor and NVIDIA graphics, this gaming beast delivers smooth gameplay and immersive visuals. With its durable construction and advanced cooling system, the FX506LHB is ready to take on any gaming challenge you throw its way.', 15490000, 0, 1500, '1 years', 1, NULL, NULL, NULL, 2, NULL),
(3, NULL, 'Introducing the Gigabyte Gaming G5 GD i5 11400H, a powerhouse designed for immersive gaming experiences. Packed with the latest 11th generation Intel Core i5 11400H processor, it delivers exceptional performance for both gaming and multitasking. With its sleek design and cutting-edge features, the Gigabyte Gaming G5 GD i5 11400H is the ultimate gaming laptop for enthusiasts and professionals alike.', 17990000, 0, 100, '1 years', 1, NULL, NULL, NULL, 3, NULL),
(4, '<div class=\"sketchfab-embed-wrapper\"> <iframe title=\"Lenovo Legion Y530 lowpoly\" frameborder=\"0\" allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" allow=\"autoplay; fullscreen; xr-spatial-tracking\" xr-spatial-tracking execution-while-out-of-viewport execution-while-not-rendered web-share width=\"640\" height=\"320\" src=\"https://sketchfab.com/models/035833dbcdac42d9bdf67ef1c7cba029/embed?autostart=1\"> </iframe> <p style=\"font-size: 13px; font-weight: normal; margin: 5px; color: #4A4A4A;\"> <a href=\"https://sketchfab.com/3d-models/lenovo-legion-y530-lowpoly-035833dbcdac42d9bdf67ef1c7cba029?utm_medium=embed&utm_campaign=share-popup&utm_content=035833dbcdac42d9bdf67ef1c7cba029\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> Lenovo Legion Y530 lowpoly </a> by <a href=\"https://sketchfab.com/EXO-S?utm_medium=embed&utm_campaign=share-popup&utm_content=035833dbcdac42d9bdf67ef1c7cba029\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> EXO-S </a> on <a href=\"https://sketchfab.com?utm_medium=embed&utm_campaign=share-popup&utm_content=035833dbcdac42d9bdf67ef1c7cba029\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\">Sketchfab</a></p></div>', 'Introducing the Lenovo Gaming Legion 5 15IAH7H, powered by the impressive i5-12500H processor, delivering exceptional gaming performance. This cutting-edge laptop combines sleek design with powerful hardware, offering an immersive gaming experience like never before. With its high refresh rate display and advanced cooling system, the Legion 5 15IAH7H is ready to elevate your gaming adventures to new heights.', 26990005, 0, 500, '1 years', 1, NULL, NULL, NULL, 4, NULL),
(5, '<div class=\"sketchfab-embed-wrapper\"> <iframe title=\"Gaming Laptop MSI 3D model\" frameborder=\"0\" allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" allow=\"autoplay; fullscreen; xr-spatial-tracking\" xr-spatial-tracking execution-while-out-of-viewport execution-while-not-rendered web-share width=\"640\" height=\"320\" src=\"https://sketchfab.com/models/7ca905c52146404c8c3fb245a97e480d/embed?autostart=1\"> </iframe> <p style=\"font-size: 13px; font-weight: normal; margin: 5px; color: #4A4A4A;\"> <a href=\"https://sketchfab.com/3d-models/gaming-laptop-msi-3d-model-7ca905c52146404c8c3fb245a97e480d?utm_medium=embed&utm_campaign=share-popup&utm_content=7ca905c52146404c8c3fb245a97e480d\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> Gaming Laptop MSI 3D model </a> by <a href=\"https://sketchfab.com/eriklensherr998?utm_medium=embed&utm_campaign=share-popup&utm_content=7ca905c52146404c8c3fb245a97e480d\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> exnihilo </a> on <a href=\"https://sketchfab.com?utm_medium=embed&utm_campaign=share-popup&utm_content=7ca905c52146404c8c3fb245a97e480d\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\">Sketchfab</a></p></div>', 'Introducing the powerhouse of gaming performance, the MSI Gaming GF63 Thin 11SC-664VN i5-11400H laptop. Designed to fuel your gaming adventures, it boasts a high-performance Intel Core i5-11400H processor for seamless gameplay and multitasking. With its sleek and thin design, immerse yourself in stunning visuals and lightning-fast speeds, making this laptop the ultimate gaming companion.', 14990000, 0, 200, '1 years', 1, NULL, NULL, NULL, 5, NULL),
(6, '<div class=\"sketchfab-embed-wrapper\"> <iframe title=\"MSI GT72S Dominator Pro\" frameborder=\"0\" allowfullscreen mozallowfullscreen=\"true\" webkitallowfullscreen=\"true\" allow=\"autoplay; fullscreen; xr-spatial-tracking\" xr-spatial-tracking execution-while-out-of-viewport execution-while-not-rendered web-share width=\"640\" height=\"320\" src=\"https://sketchfab.com/models/7606a39ada1446aba3cdd825d02120a0/embed?autostart=1\"> </iframe> <p style=\"font-size: 13px; font-weight: normal; margin: 5px; color: #4A4A4A;\"> <a href=\"https://sketchfab.com/3d-models/msi-gt72s-dominator-pro-7606a39ada1446aba3cdd825d02120a0?utm_medium=embed&utm_campaign=share-popup&utm_content=7606a39ada1446aba3cdd825d02120a0\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> MSI GT72S Dominator Pro </a> by <a href=\"https://sketchfab.com/msicomputers?utm_medium=embed&utm_campaign=share-popup&utm_content=7606a39ada1446aba3cdd825d02120a0\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\"> MSI Computers </a> on <a href=\"https://sketchfab.com?utm_medium=embed&utm_campaign=share-popup&utm_content=7606a39ada1446aba3cdd825d02120a0\" target=\"_blank\" rel=\"nofollow\" style=\"font-weight: bold; color: #1CAAD9;\">Sketchfab</a></p></div>', 'Introducing the MSI Gaming Alpha 15 B5EEK-203VN R5 5600H, a powerhouse designed for immersive gaming experiences. With its cutting-edge AMD Ryzen 5 5600H processor, this laptop delivers exceptional performance and responsiveness. Paired with the advanced graphics capabilities and stunning display, it\'s the ultimate gaming companion for enthusiasts seeking top-tier performance.', 17990000, 0, 300, '1 years', 1, NULL, NULL, NULL, 6, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product_discount`
--

CREATE TABLE `product_discount` (
  `id` bigint(20) NOT NULL,
  `coupon_code` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `discount_unit` varchar(255) DEFAULT NULL,
  `discount_value` double NOT NULL,
  `valid_until` datetime DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ram`
--

CREATE TABLE `ram` (
  `id` bigint(20) NOT NULL,
  `bus` smallint(6) NOT NULL,
  `model` varchar(50) DEFAULT NULL,
  `ram_type` varchar(15) DEFAULT NULL,
  `size` smallint(6) NOT NULL,
  `manufacturer` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ram`
--

INSERT INTO `ram` (`id`, `bus`, `model`, `ram_type`, `size`, `manufacturer`) VALUES
(1, 3200, NULL, 'DDR4', 8, NULL),
(2, 2933, NULL, 'DDR4', 8, NULL),
(3, 4800, NULL, 'DDR5', 8, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `role_name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_MEMBER'),
(3, 'ROLE_SHIPPER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `full_name` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `provider` varchar(15) DEFAULT NULL,
  `cart_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `address`, `email`, `full_name`, `password`, `phone_number`, `provider`, `cart_id`) VALUES
(1, 'Ha Noi', 'admin@gmail.com', 'Hung Vip Pro', '$2a$10$Pq98/IeFsJ5Q3GHoye1qNepUN44Pdjo37zVSiIQDXjnvYONIqC7Qu', '123456789', NULL, NULL),
(2, 'Ha Noi', 'member@gmail.com', 'Sir Talion Member', '$2a$10$VoM1k4qgWXcH.Vm8e6azj.uRbm/73RP0s.9F12msuWvttlym66JEi', '123456789', NULL, 5),
(3, 'Ha Noi', 'shipper@gmail.com', 'Sir Talion Shipper', '$2a$10$iaEUrze2ke7ey6bBhU2cfO6W0n/ymhg6rgPJP/3m7vUVfpir5hNay', '123456789', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_responses`
--

CREATE TABLE `user_responses` (
  `user_id` bigint(20) NOT NULL,
  `responses_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user` bigint(20) NOT NULL,
  `role` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user`, `role`) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3),
(3, 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `battery`
--
ALTER TABLE `battery`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKk4iu99d2pp9qbgqq35u6kpyif` (`order_id`),
  ADD KEY `FKbsutcik66rr898m5tcyn6fhcp` (`purchaser_id`);

--
-- Chỉ mục cho bảng `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `cart_info`
--
ALTER TABLE `cart_info`
  ADD PRIMARY KEY (`cart_id`,`product_id`),
  ADD KEY `FKpywhv45hp7qdwhpv7yu6rjag` (`product_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `cpu`
--
ALTER TABLE `cpu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3jipsleux44v79lak37slha77` (`manufacturer`);

--
-- Chỉ mục cho bảng `display`
--
ALTER TABLE `display`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `drive`
--
ALTER TABLE `drive`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqx4y00wywa1lnph558hiwnw5e` (`manufacturer`);

--
-- Chỉ mục cho bảng `graphic_card`
--
ALTER TABLE `graphic_card`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf5rwekwi33ds1si6o51df3321` (`manufacturer`);

--
-- Chỉ mục cho bảng `laptop`
--
ALTER TABLE `laptop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1lu89yn34qobnglq3h5urf0ql` (`battery`),
  ADD KEY `FKqudsarp6k46t8nonq6sg050f3` (`display`),
  ADD KEY `FKjh1k6t9t9s2q05cxhxug8impx` (`manufacturer`),
  ADD KEY `FKe7dit4kt5rdaoksi47a8oi2kw` (`operating_system`);

--
-- Chỉ mục cho bảng `laptop_cpu`
--
ALTER TABLE `laptop_cpu`
  ADD KEY `FKe14g9tgq6msxat1gxjtfa3rkx` (`cpu`),
  ADD KEY `FKqa6fvirvmtdv7podr3ryy6ckm` (`laptop`);

--
-- Chỉ mục cho bảng `laptop_drive`
--
ALTER TABLE `laptop_drive`
  ADD KEY `FK2vxee2saxcgcs9bvv4xxg7n5h` (`drive`),
  ADD KEY `FK6ioqnw2wci9p0ovdtdn5va11n` (`laptop`);

--
-- Chỉ mục cho bảng `laptop_graphic_card`
--
ALTER TABLE `laptop_graphic_card`
  ADD KEY `FKn6k5x365tsfbuwjs2q374rmc8` (`graphic_card`),
  ADD KEY `FK4yfglr985lrl1mbd2fxprnyvx` (`laptop`);

--
-- Chỉ mục cho bảng `laptop_ram`
--
ALTER TABLE `laptop_ram`
  ADD KEY `FK9kvsm4wcci0vccr9p8qhuugye` (`ram`),
  ADD KEY `FKovccbva8mykdts03bawtkbloc` (`laptop`);

--
-- Chỉ mục cho bảng `manufacturer`
--
ALTER TABLE `manufacturer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `operating_system`
--
ALTER TABLE `operating_system`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK66jps9g3y0ul5typfgrjba0l` (`shipper_id`);

--
-- Chỉ mục cho bảng `order_discount`
--
ALTER TABLE `order_discount`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKuavxqbi3h98bdxxwgm4cw51y` (`order_id`);

--
-- Chỉ mục cho bảng `order_info`
--
ALTER TABLE `order_info`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `FKbn4h6gy4gbq1yt0vg4ghj5pk8` (`product_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  ADD KEY `FK1ho0qo6nwxuqkfq1knxbfbvbj` (`cpu`),
  ADD KEY `FKs47nlvndbb9jk32hakdba6v2r` (`drive`),
  ADD KEY `FK6yc83c0ms4eya5j4xeyk1gpei` (`graphic_card`),
  ADD KEY `FK6rs6g8wxtdq5qcnx2g12n50ro` (`laptop`),
  ADD KEY `FK8mnqdwvvhrodndojonwx9p7c2` (`ram`);

--
-- Chỉ mục cho bảng `product_discount`
--
ALTER TABLE `product_discount`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr5ttw8wovl5nkcc9ysfc16fkk` (`product_id`);

--
-- Chỉ mục cho bảng `ram`
--
ALTER TABLE `ram`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp0460dmlw6kq49ckuon9f8kyl` (`manufacturer`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtqa69bib34k2c0jhe7afqsao6` (`cart_id`);

--
-- Chỉ mục cho bảng `user_responses`
--
ALTER TABLE `user_responses`
  ADD UNIQUE KEY `UK_2921lyhsw5ig4r06x3yvjj5f1` (`responses_id`),
  ADD KEY `FKti6mo76xpvlgc07ounl6c4in` (`user_id`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD KEY `FK26f1qdx6r8j1ggkgras9nrc1d` (`role`),
  ADD KEY `FKmow7bmkl6wduuutk26ypkgmm1` (`user`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `battery`
--
ALTER TABLE `battery`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `cart`
--
ALTER TABLE `cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `contact`
--
ALTER TABLE `contact`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `cpu`
--
ALTER TABLE `cpu`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `display`
--
ALTER TABLE `display`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `drive`
--
ALTER TABLE `drive`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `graphic_card`
--
ALTER TABLE `graphic_card`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `laptop`
--
ALTER TABLE `laptop`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `manufacturer`
--
ALTER TABLE `manufacturer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `operating_system`
--
ALTER TABLE `operating_system`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `order_discount`
--
ALTER TABLE `order_discount`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `product_discount`
--
ALTER TABLE `product_discount`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `ram`
--
ALTER TABLE `ram`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `FKbsutcik66rr898m5tcyn6fhcp` FOREIGN KEY (`purchaser_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKk4iu99d2pp9qbgqq35u6kpyif` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `cart_info`
--
ALTER TABLE `cart_info`
  ADD CONSTRAINT `FK4kd6daop45kt6wjpvp5nydyma` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  ADD CONSTRAINT `FKpywhv45hp7qdwhpv7yu6rjag` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `cpu`
--
ALTER TABLE `cpu`
  ADD CONSTRAINT `FK3jipsleux44v79lak37slha77` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`);

--
-- Các ràng buộc cho bảng `drive`
--
ALTER TABLE `drive`
  ADD CONSTRAINT `FKqx4y00wywa1lnph558hiwnw5e` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`);

--
-- Các ràng buộc cho bảng `graphic_card`
--
ALTER TABLE `graphic_card`
  ADD CONSTRAINT `FKf5rwekwi33ds1si6o51df3321` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`);

--
-- Các ràng buộc cho bảng `laptop`
--
ALTER TABLE `laptop`
  ADD CONSTRAINT `FK1lu89yn34qobnglq3h5urf0ql` FOREIGN KEY (`battery`) REFERENCES `battery` (`id`),
  ADD CONSTRAINT `FKe7dit4kt5rdaoksi47a8oi2kw` FOREIGN KEY (`operating_system`) REFERENCES `operating_system` (`id`),
  ADD CONSTRAINT `FKjh1k6t9t9s2q05cxhxug8impx` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`),
  ADD CONSTRAINT `FKqudsarp6k46t8nonq6sg050f3` FOREIGN KEY (`display`) REFERENCES `display` (`id`);

--
-- Các ràng buộc cho bảng `laptop_cpu`
--
ALTER TABLE `laptop_cpu`
  ADD CONSTRAINT `FKe14g9tgq6msxat1gxjtfa3rkx` FOREIGN KEY (`cpu`) REFERENCES `cpu` (`id`),
  ADD CONSTRAINT `FKqa6fvirvmtdv7podr3ryy6ckm` FOREIGN KEY (`laptop`) REFERENCES `laptop` (`id`);

--
-- Các ràng buộc cho bảng `laptop_drive`
--
ALTER TABLE `laptop_drive`
  ADD CONSTRAINT `FK2vxee2saxcgcs9bvv4xxg7n5h` FOREIGN KEY (`drive`) REFERENCES `drive` (`id`),
  ADD CONSTRAINT `FK6ioqnw2wci9p0ovdtdn5va11n` FOREIGN KEY (`laptop`) REFERENCES `laptop` (`id`);

--
-- Các ràng buộc cho bảng `laptop_graphic_card`
--
ALTER TABLE `laptop_graphic_card`
  ADD CONSTRAINT `FK4yfglr985lrl1mbd2fxprnyvx` FOREIGN KEY (`laptop`) REFERENCES `laptop` (`id`),
  ADD CONSTRAINT `FKn6k5x365tsfbuwjs2q374rmc8` FOREIGN KEY (`graphic_card`) REFERENCES `graphic_card` (`id`);

--
-- Các ràng buộc cho bảng `laptop_ram`
--
ALTER TABLE `laptop_ram`
  ADD CONSTRAINT `FK9kvsm4wcci0vccr9p8qhuugye` FOREIGN KEY (`ram`) REFERENCES `ram` (`id`),
  ADD CONSTRAINT `FKovccbva8mykdts03bawtkbloc` FOREIGN KEY (`laptop`) REFERENCES `laptop` (`id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK66jps9g3y0ul5typfgrjba0l` FOREIGN KEY (`shipper_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `order_discount`
--
ALTER TABLE `order_discount`
  ADD CONSTRAINT `FKuavxqbi3h98bdxxwgm4cw51y` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `order_info`
--
ALTER TABLE `order_info`
  ADD CONSTRAINT `FKbn4h6gy4gbq1yt0vg4ghj5pk8` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKnex6bxsweinykaoxbvv2uop5a` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1ho0qo6nwxuqkfq1knxbfbvbj` FOREIGN KEY (`cpu`) REFERENCES `cpu` (`id`),
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK6rs6g8wxtdq5qcnx2g12n50ro` FOREIGN KEY (`laptop`) REFERENCES `laptop` (`id`),
  ADD CONSTRAINT `FK6yc83c0ms4eya5j4xeyk1gpei` FOREIGN KEY (`graphic_card`) REFERENCES `graphic_card` (`id`),
  ADD CONSTRAINT `FK8mnqdwvvhrodndojonwx9p7c2` FOREIGN KEY (`ram`) REFERENCES `ram` (`id`),
  ADD CONSTRAINT `FKs47nlvndbb9jk32hakdba6v2r` FOREIGN KEY (`drive`) REFERENCES `drive` (`id`);

--
-- Các ràng buộc cho bảng `product_discount`
--
ALTER TABLE `product_discount`
  ADD CONSTRAINT `FKr5ttw8wovl5nkcc9ysfc16fkk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Các ràng buộc cho bảng `ram`
--
ALTER TABLE `ram`
  ADD CONSTRAINT `FKp0460dmlw6kq49ckuon9f8kyl` FOREIGN KEY (`manufacturer`) REFERENCES `manufacturer` (`id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKtqa69bib34k2c0jhe7afqsao6` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

--
-- Các ràng buộc cho bảng `user_responses`
--
ALTER TABLE `user_responses`
  ADD CONSTRAINT `FKfn3l6h68qjwmkuo2jrnedaasp` FOREIGN KEY (`responses_id`) REFERENCES `contact` (`id`),
  ADD CONSTRAINT `FKti6mo76xpvlgc07ounl6c4in` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK26f1qdx6r8j1ggkgras9nrc1d` FOREIGN KEY (`role`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKmow7bmkl6wduuutk26ypkgmm1` FOREIGN KEY (`user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
