/* Sximo builder 
	copyright 2014 . sximo builder com 
*/

jQuery(document).ready(function($){
		if($.cookie("sxintheme") != '')
		{
			$('#switchTheme').attr('href',$.cookie("sxintheme"));
		} 		
		if($.cookie("sximo-sidebar") =='minimize-sidemenu'){
			$("body").addClass("minimize-sidemenu");
			$('#sidemenu').removeClass('expanded-menu');
		} else {
			$("body").removeClass("minimize-sidemenu");
			$('#sidemenu').addClass('expanded-menu');
		}
		$(window).bind("load resize", function() {
			if ($(this).width() < 769) {
				$('body').addClass('body-small')
			} else {
				$('body').removeClass('body-small')
			}
		})
      /*Return to top*/
      var offset = 220;
      var duration = 500;
      var button = $('<a href="#" class="back-to-top"><i class="fa fa-angle-up"></i></a>');
      button.appendTo("body");
      
      jQuery(window).scroll(function() {
        if (jQuery(this).scrollTop() > offset) {
            jQuery('.back-to-top').fadeIn(duration);
        } else {
            jQuery('.back-to-top').fadeOut(duration);
        }
      });
    
      jQuery('.back-to-top').click(function(event) {
          event.preventDefault();
          jQuery('html, body').animate({scrollTop: 0}, duration);
          return false;
      });

  	$('.switch').bootstrapSwitch();
	$('.date').datepicker({format:'yyyy-mm-dd',autoClose:true})
	$('.datetime').datetimepicker({format: 'yyyy-mm-dd hh:ii:ss'}); 
	
	/* Tooltip */
	$('.previewImage').fancybox();	
	$('.tips').tooltip();	
	$('.editor').summernote();
	$(".select2").select2({ width:"98%"});	
	$(".select-liquid").select2({
		minimumResultsForSearch: "-1",
	});	
	$('.panel-trigger').click(function(e){
		e.preventDefault();
		$(this).toggleClass('active');
	});

	$('.dropdown, .btn-group').on('show.bs.dropdown', function(e){
		$(this).find('.dropdown-menu').first().stop(true, true).fadeIn(100);
	});
	$('.dropdown, .btn-group').on('hide.bs.dropdown', function(e){
		$(this).find('.dropdown-menu').first().stop(true, true).fadeOut(100);
	});
	$('.popup').click(function (e) {
		e.stopPropagation();
	});	
     window.prettyPrint && prettyPrint();

	$(".checkall").click(function() {
		var cblist = $(".ids");
		if($(this).is(":checked"))
		{				
			cblist.prop("checked", !cblist.is(":checked"));
		} else {	
			cblist.removeAttr("checked");
		}	
	});

    if(location.search.indexOf('sort=order') > 0){
        $('table tbody').sortable({
            axis:'y',
            cursor: 'move',
            cancel:'#sximo-quick-search',
            forceHelperSize: true,
            helper: "clone",
            start:function(e,ui){
                console.log('start');
            },
            sort:function(e,ui){
                //ui.helper.css('position','relative');
            },
            update:function(e,ui){
                var item = ui.item,
                    s = 'td:eq(2)',
                    id = item.find(s).text().trim(),
                    pid = item.prev('tr').find(s).text().trim(),
                    nid = item.next('tr').find(s).text().trim(),
                    table = $('input[name=table]').val(),
                    url = '/orders/reorder';
                console.log(id+" "+pid+" "+nid+' '+table);
                $.post(
                    url,
                    {'id':id,'pid':pid,'nid':nid, 'table':table},
                    function(resp){
                        if(resp.success){
                            location.href = resp.redirect;
                        }
                    }
                );
            }
        });
    }

	$('.nav li ul li.active').parents('li').addClass('active');
	
	
		$('input[type="checkbox"],input[type="radio"]').iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green',
		});	
		$('.checkall').on('ifChecked',function(){
			$('input[type="checkbox"]').iCheck('check');
		});
		$('.checkall').on('ifUnchecked',function(){
			$('input[type="checkbox"]').iCheck('uncheck');
		});	
    $('.navbar-minimalize').click(function () {
      var w = $("body");
		w.toggleClass("minimize-sidemenu");
			
		if( w.hasClass('minimize-sidemenu'))
		{
			$('#sidemenu').removeClass('expanded-menu');
			$.cookie("sximo-sidebar",'minimize-sidemenu', {expires: 365, path: '/'});
		} else {
			$('#sidemenu').addClass('expanded-menu');
			 $.cookie("sximo-sidebar",'maximaze-sidemenu', {expires: 365, path: '/'});	
		}		
    })	
})
function SximoConfirmDelete( url )
{
	if(confirm('Are u sure deleting this record ? '))
	{
		window.location.href = url;	
	}
	return false;
}
function SximoDelete(  )
{	
	var total = $('input[class="ids"]:checkbox:checked').length;
	if(confirm('are u sure removing selected rows ?'))
	{
			$('#SximoTable').submit();// do the rest here	
	}	
}	
function SximoModal( url , title)
{
	$('#sximo-modal-content').html(' ....Loading content , please wait ...');
	$('.modal-title').html(title);
	$('#sximo-modal-content').load(url,function(){
	});
	$('#sximo-modal').modal('show');	
}

;(function ($, window, document, undefined) {

    var pluginName = "sximMenu",
        defaults = {
            toggle: true
        };

    function Plugin(element, options) {
        this.element = element;
        this.settings = $.extend({}, defaults, options);
        this._defaults = defaults;
        this._name = pluginName;
        this.init();
    }

    Plugin.prototype = {
        init: function () {

            var $this = $(this.element),
                $toggle = this.settings.toggle;

            $this.find('li.active').has('ul').children('ul').addClass('collapse in');
            $this.find('li').not('.active').has('ul').children('ul').addClass('collapse');

            $this.find('li').has('ul').children('a').on('click', function (e) {
                e.preventDefault();

                $(this).parent('li').toggleClass('active').children('ul').collapse('toggle');

                if ($toggle) {
                    $(this).parent('li').siblings().removeClass('active').children('ul.in').collapse('hide');
                }
            });
        }
    };

    $.fn[ pluginName ] = function (options) {
        return this.each(function () {
            if (!$.data(this, "plugin_" + pluginName)) {
                $.data(this, "plugin_" + pluginName, new Plugin(this, options));
            }
        });
    };

})(jQuery, window, document);