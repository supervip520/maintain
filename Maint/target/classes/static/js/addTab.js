function changeFrameHeight(ifm) {
	ifm.height = document.documentElement.clientHeight - 118;
}
function resizeFrameHeight() {
	$('.tab_iframe').css('height', document.documentElement.clientHeight - 118);
	$('md-tab-content').css('left', '0');
}
// 选项卡对象
var Tab = {
	addTab: function(title, url) {
		var index = url.replace(/\./g, '_').replace(/\//g, '_').replace(/:/g, '_').replace(/\?/g, '_').replace(/,/g, '_').replace(/=/g, '_').replace(/&/g, '_');
		// 如果存在选项卡，则激活，否则创建新选项卡
		if ($('#tab_' + index).length == 0) {
			// 添加选项卡
			var tab = '<li id="tab_' + index +'" data-index="' + index + '" class="cur"><a class="waves-effect waves-light">' + title + '</a></li>';//<i class="zmdi zmdi-close"></i><
			$('.content_tab>ul').append(tab);
			// 添加iframe
			var iframe = '<div id="iframe_' + index + '" class="iframe cur"><iframe class="tab_iframe" src="' + url + '" width="100%" frameborder="0" scrolling="auto" onload="changeFrameHeight(this)"></iframe></div>';
			$('.content_main').append(iframe);
			initScrollShow();
			$('.content_tab1>ul').animate({scrollLeft: document.getElementById('tabs').scrollWidth - document.getElementById('tabs').clientWidth}, 200, function() {
				alert(2);
				initScrollState();
			});
		} else {
			$('#tab_' + index).trigger('click');
		}
		// 关闭侧边栏
	},
	closeTab: function($item) {
		var closeable = $item.data('closeable');
		if (closeable != false) {
			// 如果当前时激活状态则关闭后激活左边选项卡
			if($item.hasClass('cur')) {
				$item.prev().trigger('click');
			}
			// 关闭当前选项卡
			var index = $item.data('index');
			$('#iframe_' + index).remove();
			$item.remove();
		}
		initScrollShow();
	}
}
function initScrollShow() {
		$('.content_tab').addClass('scroll');
}
function initScrollState() {
	if ($('.content_tab>ul').scrollLeft() == 0) {
		$('.tab_left>a').removeClass('active');
	} else {
		$('.tab_left>a').addClass('active');
	}
	if (($('.content_tab>ul').scrollLeft() + document.getElementById('tabs').clientWidth) >= document.getElementById('tabs').scrollWidth) {
		$('.tab_right>a').removeClass('active');
	} else {
		$('.tab_right>a').addClass('active');
	}
}

function fullPage() {

	if ($.util.supportsFullScreen) {
		if ($.util.isFullScreen()) {
			$.util.cancelFullScreen();
		} else {
			$.util.requestFullScreen();
		}
	} else {
		alert("当前浏览器不支持全屏 API，请更换至最新的 Chrome/Firefox/Safari 浏览器或通过 F11 快捷键进行操作。");
	}
}