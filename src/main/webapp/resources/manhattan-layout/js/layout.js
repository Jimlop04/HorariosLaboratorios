/** 
 * PrimeFaces Manhattan Layout
 */
PrimeFaces.widget.Manhattan = PrimeFaces.widget.BaseWidget.extend({
    
    init: function(cfg) {
        this._super(cfg);
        this.wrapper = $(document.body).children('.layout-wrapper');
        this.contentWrapper = this.wrapper.children('.layout-main');
        this.sidebar = this.wrapper.children('.layout-sidebar');
        this.menu = this.sidebar.find('.layout-menu');
        this.menulinks = this.menu.find('a');
        this.rightPanel = this.wrapper.children('.layout-rightpanel');
        this.topbar = this.contentWrapper.children('.layout-topbar');
        this.menuContainer = this.wrapper.children('.layout-menu-container');
        this.menuButton = $('#layout-menu-btn');
        this.topbarMenu = this.topbar.find('> .layout-topbar-menu-wrapper > .topbar-menu');
        this.topbarItems = this.topbarMenu.children('li');
        this.topbarLinks = this.topbarMenu.find('a');
        this.rightPanelButton = this.topbar.find('> .layout-topbar-menu-wrapper > .rightpanel-btn');
        this.topbarMenuButton = this.topbar.find('> .layout-topbar-menu-wrapper > .topbar-menu-btn');
        this.anchorButton = $('#layout-sidebar-anchor');

        this.configButton = $('#layout-config-button');
        this.configurator = this.wrapper.children('.layout-config');
        this.configClicked = false;

        this.bindEvents();

        if(!this.isHorizontal()) {
            this.restoreMenuState();
        }
        console.log("Funciona!");
        this.expandedMenuitems = this.expandedMenuitems||[];
    },
     
    bindEvents: function() {
        var $this = this;
        
        this.sidebar.off('mouseenter.sidebar mouseleave.sidebar click.sidebar').on('mouseenter.sidebar', function(e) {
            if($this.isSlim()) {
                if($this.hideTimeout) {
                    clearTimeout($this.hideTimeout);
                }
                $this.wrapper.addClass('layout-slim-active');
            }
        })
        .on('mouseleave.sidebar', function(e) {
            if($this.wrapper.hasClass('layout-slim-active')) {
                $this.hideTimeout = setTimeout(function() {
                    $this.wrapper.removeClass('layout-slim-active');
                }, 250); 
            }
        })
        .on('click.sidebar', function(e) {
            $this.sidebarClick = true;
        });

        $this.menu.off('click.menu').on('click.menu', function() {
            $this.menuClick = true;
        });
        
        $this.menulinks.off('click.menu').on('click.menu', function (e) {
            var link = $(this),
            item = link.parent('li'),
            submenu = item.children('ul');

            if (item.hasClass('active-menuitem')) {
                if (submenu.length) {
                    $this.removeMenuitem(item.attr('id'));
                    item.removeClass('active-menuitem');

                    if($this.isHorizontal())
                        submenu.hide();
                    else
                        submenu.slideUp();
                }

                if(item.parent().is($this.jq)) {
                    $this.menuActive = false;
                }
            }
            else {
                $this.addMenuitem(item.attr('id'));

                if($this.isHorizontal()) {
                    $this.deactivateItems(item.siblings(), false);

                    if(submenu.length === 0) {
                        $this.resetMenu();
                    }
                }
                else {
                    $this.deactivateItems(item.siblings(), true);
                    $.cookie('manhattan_menu_scroll_state', link.attr('href') + ',' + $this.menuContainer.scrollTop(), { path: '/' });
                }
                
                $this.activate(item);
                
                if(item.parent().is($this.jq)) {
                    $this.menuActive = true;
                }
            }

            if (submenu.length) {
                e.preventDefault();
            }
        });

        $this.menu.find('> li').off('mouseenter.menu').on('mouseenter.menu', function(e) {    
            if($this.isHorizontal()) {
                var item = $(this);
                
                if(!item.hasClass('active-menuitem')) {
                    $this.menu.find('.active-menuitem').removeClass('active-menuitem');
                    $this.menu.find('ul:visible').hide();
                    
                    
                    if($this.menuActive) {
                        item.addClass('active-menuitem');
                        item.children('ul').show();
                    }
                }
            }
        });
        
        this.topbarLinks.off('click.topbar').on('click.topbar', function(e) {
            var link = $(this),
            item = link.parent(),
            submenu = link.next();
            
            $this.topbarClick = true;

            item.siblings('.active-topmenuitem').removeClass('active-topmenuitem');

            if($this.isMobile()) {
                item.children('ul').removeClass('fadeInDown fadeOutUp');
                item.toggleClass('active-topmenuitem');
            }
            else {
                if(submenu.length) {
                    if(item.hasClass('active-topmenuitem')) {
                        submenu.addClass('fadeOutUp');
                        
                        setTimeout(function() {
                            item.removeClass('active-topmenuitem'),
                            submenu.removeClass('fadeOutUp');
                        },450);
                    }
                    else {
                        item.addClass('active-topmenuitem');
                        submenu.addClass('fadeInDown');
                    }
                }
            }   
            
            var href = link.attr('href');
            if(href && href !== '#') {
                window.location.href = href;
            }
                        
            e.preventDefault();   
        });
        
        this.rightPanelButton.off('click.rightpanel').on('click.rightpanel', function(e) {
            $this.rightPanelClick = true;
            $this.rightPanel.toggleClass('layout-rightpanel-active');
            $this.rightPanelButton.toggleClass('rightpanel-btn-active');
            e.preventDefault();
        });
        
        this.rightPanel.off('click.rightpanel').on('click.rightpanel', function(e) {
            $this.rightPanelClick = true;
        });
        
        this.anchorButton.off('click.menu').on('click.menu', function(e) {
            $this.wrapper.removeClass('layout-slim-restore');
            $this.wrapper.toggleClass('layout-slim-anchored');
            $this.saveMenuState();
            
            setTimeout(function() {
                $(window).trigger('resize');
            }, 200);
            
            e.preventDefault();
        });
        
        this.menuButton.off('click.menu').on('click.menu', function(e) {
            $this.menuClick = true;

            if($this.isMobile()) {
                $this.wrapper.toggleClass('layout-mobile-active');
            }
            else {
                if($this.isStatic())
                    $this.wrapper.toggleClass('layout-static-inactive');
                else if($this.isOverlay())
                    $this.wrapper.toggleClass('layout-overlay-active');
                else if($this.isToggle())
                    $this.wrapper.toggleClass('layout-toggle-active');
            }

            setTimeout(function() {
                $(window).trigger('resize');
            }, 350);  

            e.preventDefault();
        });
        
        this.topbarMenuButton.off('click.topbar').on('click.topbar', function(e) {
            $this.topbarClick = true;
            $this.topbarMenu.find('ul').removeClass('fadeInDown fadeOutUp');

            if($this.topbarMenu.hasClass('topbar-menu-active')) {
                $this.topbarMenu.addClass('fadeOutUp');
                
                setTimeout(function() {
                    $this.topbarMenu.removeClass('fadeOutUp topbar-menu-active');
                },400);
            }
            else {
                $this.topbarMenu.addClass('topbar-menu-active fadeInDown');
            }
                        
            e.preventDefault();
        });
        
        this.contentWrapper.children('.layout-main-mask').off('click.mask').on('click.mask', function(e) {
            $this.wrapper.removeClass('layout-mobile-active layout-slim-restore');
            $(document.body).removeClass('hidden-overflow');
        });

        this.configButton.off('click.configbutton').on('click.configbutton', function(e) {
            $this.configurator.toggleClass('layout-config-active');
            $this.configClicked = true;
        });

        this.configurator.off('click.config').on('click.config', function() {
            $this.configClicked = true;
        });
        
        $(document.body).off('click.layoutBody').on('click.layoutBody', function() {
            if(!$this.menuClick && $this.isHorizontal()) {
                $this.menu.find('.active-menuitem').removeClass('active-menuitem');
                $this.menu.find('ul:visible').hide();
                $this.menuActive = false;
            }

            if(!$this.menuClick && !$this.sidebarClick) {
                $this.wrapper.removeClass('layout-overlay-active layout-toggle-active');
            }

            if(!$this.topbarClick) {
                $this.topbarItems.filter('.active-topmenuitem').removeClass('active-topmenuitem');
                $this.topbarMenu.removeClass('topbar-menu-active');
            }
            
            if(!$this.rightPanelClick && !$this.rightPanelButtonClick && $this.rightPanel.hasClass('layout-rightpanel-active') && !$this.isDatePickerPanelClicked()) {
                $this.rightPanel.removeClass('layout-rightpanel-active');
                $this.rightPanelButton.removeClass('rightpanel-btn-active');
            }

            if (!$this.configClicked && $this.configurator.hasClass('layout-config-active')) {
                $this.configurator.removeClass('layout-config-active');
            }

            $this.sidebarClick = false;
            $this.menuClick = false;
            $this.topbarClick = false;
            $this.rightPanelClick = false;
            $this.rightPanelButtonClick = false;
            $this.configClicked = false;
        });
    },

    isDatePickerPanelClicked: function () {
        if ($.datepicker) {
            var input = $($.datepicker._lastInput);
            if (input.closest('.layout-rightpanel').length && $('#ui-datepicker-div').is(':visible')) {
                return true;
            }
        }
        return false;
    },
        
    activate: function(item) {
        var submenu = item.children('ul');
        item.addClass('active-menuitem');

        if(submenu.length) {
            if(this.isHorizontal())
                submenu.show();
            else
                submenu.slideDown();
        }
    },

    deactivate: function(item) {
        var submenu = item.children('ul');
        item.removeClass('active-menuitem');

        if(submenu.length) {
            submenu.hide();
        }
    },

    deactivateItems: function(items, animate) {
        var $this = this;

        for(var i = 0; i < items.length; i++) {
            var item = items.eq(i),
            submenu = item.children('ul');

            if(submenu.length) {
                if(item.hasClass('active-menuitem')) {
                    var activeSubItems = item.find('.active-menuitem');
                    item.removeClass('active-menuitem');

                    if(animate) {
                        submenu.slideUp('normal', function() {
                            $(this).parent().find('.active-menuitem').each(function() {
                                $this.deactivate($(this));
                            });
                        });
                    }
                    else {
                        submenu.hide();
                        item.find('.active-menuitem').each(function() {
                            $this.deactivate($(this));
                        });
                    }

                    $this.removeMenuitem(item.attr('id'));
                    activeSubItems.each(function() {
                        $this.removeMenuitem($(this).attr('id'));
                    });
                }
                else {
                    item.find('.active-menuitem').each(function() {
                        var subItem = $(this);
                        $this.deactivate(subItem);
                        $this.removeMenuitem(subItem.attr('id'));
                    });
                }
            }
            else if(item.hasClass('active-menuitem')) {
                $this.deactivate(item);
                $this.removeMenuitem(item.attr('id'));
            }
        }
    },
    
    removeMenuitem: function (id) {
        this.expandedMenuitems = $.grep(this.expandedMenuitems, function (value) {
            return value !== id;
        });
        this.saveMenuState();
    },
    
    addMenuitem: function (id) {
        if ($.inArray(id, this.expandedMenuitems) === -1) {
            this.expandedMenuitems.push(id);
        }
        this.saveMenuState();
    },
    
    saveMenuState: function() {
        if(this.wrapper.hasClass('layout-slim-anchored'))
            $.cookie('manhattan_slim_menu_anchored', 'manhattan_slim_menu_anchored', {path: '/'});
        else
            $.removeCookie('manhattan_slim_menu_anchored', {path: '/'});
        
        $.cookie('manhattan_expandeditems', this.expandedMenuitems.join(','), {path: '/'});
    },
    
    clearMenuState: function() {
        $.removeCookie('manhattan_expandeditems', {path: '/'});
        $.removeCookie('manhattan_slim_menu_anchored', {path: '/'});
    },
    
    restoreMenuState: function() {
        var $this = this;

        var menuCookie = $.cookie('manhattan_expandeditems');
        if (menuCookie) {
            this.expandedMenuitems = menuCookie.split(',');
            for (var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if (id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g, "\\:"));
                    menuitem.addClass('active-menuitem');
                    
                    var submenu = menuitem.children('ul');
                    if(submenu.length) {
                        submenu.show();
                    }
                }
            }

            setTimeout(function() {
                $this.restoreScrollState(menuitem);
            }, 100)
        }
        
        var sidebarCookie = $.cookie('manhattan_slim_menu_anchored');
        if(sidebarCookie) {
            this.wrapper.addClass('layout-slim-anchored layout-slim-restore');
        }
    },

    restoreScrollState: function(menuitem) {
        var scrollState = $.cookie('manhattan_menu_scroll_state');
        if (scrollState) {
            var state = scrollState.split(',');
            if (state[0].startsWith(this.cfg.pathname) || this.isScrolledIntoView(menuitem, state[1])) {
                this.menuContainer.scrollTop(parseInt(state[1], 10));
            }
            else {
                this.scrollIntoView(menuitem.get(0));
                $.removeCookie('manhattan_menu_scroll_state', { path: '/' });
            }
        }
        else if (!this.isScrolledIntoView(menuitem, menuitem.scrollTop())){
            this.scrollIntoView(menuitem.get(0));
        }
    },

    scrollIntoView: function(elem) {
        if (document.documentElement.scrollIntoView) {
            elem.scrollIntoView();
        }
    },

    isScrolledIntoView: function(elem, scrollTop) {
        var viewBottom = parseInt(scrollTop, 10) + this.menuContainer.height();

        var elemTop = elem.position().top;
        var elemBottom = elemTop + elem.height();

        return ((elemBottom <= viewBottom) && (elemTop >= scrollTop));
    },

    isSlim: function() {
        return this.wrapper.hasClass('layout-slim') && this.isDesktop();
    },
    
    isStatic: function() {
        return this.wrapper.hasClass('layout-static') && this.isDesktop();
    },

    isHorizontal: function() {
        return this.wrapper.hasClass('layout-horizontal') && this.isDesktop();
    },

    isOverlay: function() {
        return this.wrapper.hasClass('layout-overlay') && this.isDesktop();
    },

    isToggle: function() {
        return this.wrapper.hasClass('layout-toggle') && this.isDesktop();
    },
      
    hideTopBar: function() {
        var $this = this;
        this.topbarMenu.addClass('fadeOutUp');
        
        setTimeout(function() {
            $this.topbarMenu.removeClass('fadeOutUp topbar-menu-visible');
        },500);
    },

    isDesktop: function() {
        return window.innerWidth > 1280;
    },
   
    isMobile: function() {
        return window.innerWidth <= 1280;
    },
    
    resetMenu: function() {
        this.menu.find('.active-menuitem').removeClass('active-menuitem');
        this.menu.find('ul:visible').hide();
        this.menuActive = false;
    }
});

PrimeFaces.ManhattanConfigurator = {

    changeMenuMode: function(menuMode) {
        var wrapper = $(document.body).children('.layout-wrapper');
        switch (menuMode) {
            case 'layout-static':
                wrapper.addClass('layout-static').removeClass('layout-overlay layout-slim layout-horizontal layout-toggle');
                this.clearLayoutState();
                break;

            case 'layout-overlay':
                wrapper.addClass('layout-overlay').removeClass('layout-static layout-slim layout-horizontal layout-toggle');
                this.clearLayoutState();
                break;

            case 'layout-slim':
                wrapper.addClass('layout-slim').removeClass('layout-static layout-overlay layout-horizontal layout-toggle');
                this.clearLayoutState();
                break;

            case 'layout-horizontal':
                wrapper.addClass('layout-horizontal').removeClass('layout-static layout-overlay layout-slim layout-toggle');
                this.clearLayoutState();
                break;

            case 'layout-toggle':
                wrapper.addClass('layout-toggle').removeClass('layout-static layout-overlay layout-slim layout-horizontal');
                this.clearLayoutState();
                break;

            default:
                wrapper.addClass('layout-static').removeClass('layout-overlay layout-slim layout-static layout-toggle');
                this.clearLayoutState();
                break;
        }
    },

    changeMenuColor: function(color) {
        var wrapper = $(document.body).children('.layout-wrapper');

        if (color === 'light')
            wrapper.addClass('layout-light');
        else
            wrapper.removeClass('layout-light');
    },

    changeLayout: function(layoutTheme) {
        var linkElement = $('link[href*="layout-"]');
        var href = linkElement.attr('href');
        var startIndexOf = href.indexOf('layout-') + 7;
        var endIndexOf = href.indexOf('.css');
        var currentColor = href.substring(startIndexOf, endIndexOf);

        this.replaceLink(linkElement, href.replace(currentColor, layoutTheme));
    },

    changeScheme: function(theme) {
        var library = 'primefaces-manhattan';
        var linkElement = $('link[href*="theme.css"]');
        var href = linkElement.attr('href');
        var index = href.indexOf(library) + 1;
        var currentTheme = href.substring(index + library.length);

        this.replaceLink(linkElement, href.replace(currentTheme, theme));
        this.changeLayout(theme);
    },

    beforeResourceChange: function() {
        PrimeFaces.ajax.RESOURCE = null;    //prevent resource append
    },

    replaceLink: function(linkElement, href) {
        PrimeFaces.ajax.RESOURCE = 'javax.faces.Resource';

        var isIE = this.isIE();

        if (isIE) {
            linkElement.attr('href', href);
        }
        else {
            var cloneLinkElement = linkElement.clone(false);

            cloneLinkElement.attr('href', href);
            linkElement.after(cloneLinkElement);

            cloneLinkElement.off('load').on('load', function() {
                linkElement.remove();
            });
        }
    },

    clearLayoutState: function() {
        var menu = PF('ManhattanMenuWidget');

        if (menu) {
            menu.clearLayoutState();
        }
    },

    isIE: function() {
        return /(MSIE|Trident\/|Edge\/)/i.test(navigator.userAgent);
    },

    updateInputStyle: function(value) {
        if (value === 'filled')
            $(document.body).addClass('ui-input-filled');
        else
            $(document.body).removeClass('ui-input-filled');
    }
};

/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD (Register as an anonymous module)
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS
        module.exports = factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {

    var pluses = /\+/g;

    function encode(s) {
        return config.raw ? s : encodeURIComponent(s);
    }

    function decode(s) {
        return config.raw ? s : decodeURIComponent(s);
    }

    function stringifyCookieValue(value) {
        return encode(config.json ? JSON.stringify(value) : String(value));
    }

    function parseCookieValue(s) {
        if (s.indexOf('"') === 0) {
            // This is a quoted cookie as according to RFC2068, unescape...
            s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
        }

        try {
            // Replace server-side written pluses with spaces.
            // If we can't decode the cookie, ignore it, it's unusable.
            // If we can't parse the cookie, ignore it, it's unusable.
            s = decodeURIComponent(s.replace(pluses, ' '));
            return config.json ? JSON.parse(s) : s;
        } catch (e) { }
    }

    function read(s, converter) {
        var value = config.raw ? s : parseCookieValue(s);
        return $.isFunction(converter) ? converter(value) : value;
    }

    var config = $.cookie = function (key, value, options) {

        // Write

        if (arguments.length > 1 && !$.isFunction(value)) {
            options = $.extend({}, config.defaults, options);

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
            }

            return (document.cookie = [
                encode(key), '=', stringifyCookieValue(value),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path ? '; path=' + options.path : '',
                options.domain ? '; domain=' + options.domain : '',
                options.secure ? '; secure' : ''
            ].join(''));
        }

        // Read

        var result = key ? undefined : {},
            // To prevent the for loop in the first place assign an empty array
            // in case there are no cookies at all. Also prevents odd result when
            // calling $.cookie().
            cookies = document.cookie ? document.cookie.split('; ') : [],
            i = 0,
            l = cookies.length;

        for (; i < l; i++) {
            var parts = cookies[i].split('='),
                name = decode(parts.shift()),
                cookie = parts.join('=');

            if (key === name) {
                // If second argument (value) is a function it's a converter...
                result = read(cookie, value);
                break;
            }

            // Prevent storing a cookie that we couldn't decode.
            if (!key && (cookie = read(cookie)) !== undefined) {
                result[name] = cookie;
            }
        }

        return result;
    };

    config.defaults = {};

    $.removeCookie = function (key, options) {
        // Must not alter options, thus extending a fresh object...
        $.cookie(key, '', $.extend({}, options, { expires: -1 }));
        return !$.cookie(key);
    };

}));

if (PrimeFaces.widget.InputSwitch) {
    PrimeFaces.widget.InputSwitch = PrimeFaces.widget.InputSwitch.extend({

        init: function (cfg) {
            this._super(cfg);

            if (this.input.prop('checked')) {
                this.jq.addClass('ui-inputswitch-checked');
            }
        },

        check: function () {
            var $this = this;

            this.input.prop('checked', true).trigger('change');
            setTimeout(function () {
                $this.jq.addClass('ui-inputswitch-checked');
            }, 100);
        },

        uncheck: function () {
            var $this = this;

            this.input.prop('checked', false).trigger('change');
            setTimeout(function () {
                $this.jq.removeClass('ui-inputswitch-checked');
            }, 100);
        }
    });
}

if (PrimeFaces.widget.AccordionPanel) {
    PrimeFaces.widget.AccordionPanel = PrimeFaces.widget.AccordionPanel.extend({

        init: function (cfg) {
            this._super(cfg);

            this.headers.last().addClass('ui-accordion-header-last');
        }
    });
}
