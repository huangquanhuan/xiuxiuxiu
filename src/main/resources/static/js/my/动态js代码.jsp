<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<!-- 这些是还没有分开的js代码，已经辨认出2大块的js的作用，已注释 -->


<!-- //bootstrap-pop-up -->
<!-- footer -->
<!-- menu -->
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/modernizr.custom.46884.js"></script>



<!-- //js -->
<script src="js/bars.js"></script>

<!-- 首页中顶部轮播图的动态效果 -->
<script type="text/javascript" src="js/jquery.slicebox.js"></script>
<script type="text/javascript">
	$(function() {
		var Page = (function() {
			var $navArrows = $('#nav-arrows').hide(), $navDots = $('#nav-dots')
					.hide(), $nav = $navDots.children('span'), $shadow = $(
					'#shadow').hide(), slicebox = $('#sb-slider').slicebox({
				onReady : function() {
					$navArrows.show();
					$navDots.show();
					$shadow.show();
				},
				onBeforeChange : function(pos) {
					$nav.removeClass('nav-dot-current');
					$nav.eq(pos).addClass('nav-dot-current');
				}
			}),
			init = function() {
				initEvents();
			}, initEvents = function() {
				// add navigation events
				$navArrows.children(':first').on('click', function() {
					slicebox.next();
					return false;
				});
				$navArrows.children(':last').on('click', function() {
					slicebox.previous();
					return false;
				});
				$nav.each(function(i) {
					$(this).on('click', function(event) {
						var $dot = $(this);
						if (!slicebox.isActive()) {
							$nav.removeClass('nav-dot-current');
							$dot.addClass('nav-dot-current');
						}

						slicebox.jump(i + 1);
						return false;
					});
				});
			};
			return {
				init : init
			};
		})();
		Page.init();
	});
</script>


<!-- Stats -->
<script src="js/waypoints.min.js"></script>
<script src="js/counterup.min.js"></script>
<script>
	jQuery(document).ready(function($) {
		$('.counter').counterUp({
			delay : 10,
			time : 2000
		});
	});
</script>





<!-- for bootstrap working -->
<script src="js/bootstrap.js"></script>
<!-- //for bootstrap working -->



<!-- 固定于右下角的回到顶部的按钮，点击可顺滑地回到顶部（下面4个js） -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<!-- start-smoth-scrolling -->
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- //end-smoth-scrolling -->
<!-- here stars scrolling icon -->
<script type="text/javascript">
	$(document).ready(function() {
		/*
			var defaults = {
			containerID: 'toTop', // fading element id
			containerHoverID: 'toTopHover', // fading element hover id
			scrollSpeed: 1200,
			easingType: 'linear' 
			};
		 */

		$().UItoTop({
			easingType : 'easeOutQuart'
		});

	});
</script>
<!-- //here ends scrolling icon -->
