/*
创建博客目录，
id表示包含博文正文的 div 容器的 id，
mt 和 st 分别表示主标题和次级标题的标签名称（如 H2、H3，大写或小写都可以！），
interval 表示移动的速度
*/
function createBlogDirectory(id, mt, st, interval) {
    //获取博文正文div容器
    var elem = document.getElementById(id);
    if (!elem) return false;
    //获取div中所有元素结点
    var nodes = elem.getElementsByTagName("*");
    //创建博客目录的div容器
    var divSideBar = document.createElement('DIV');
    divSideBar.className = 'uprightsideBar';
    divSideBar.setAttribute('id', 'uprightsideBar');
    var divSideBarTab = document.createElement('DIV');
    divSideBarTab.setAttribute('id', 'sideBarTab');
    divSideBar.appendChild(divSideBarTab);
    var h2 = document.createElement('H4');
    divSideBarTab.appendChild(h2);
    var txt = document.createTextNode('目录导航');
    h2.appendChild(txt);
    var divSideBarContents = document.createElement('DIV');
    divSideBarContents.style.display = 'none';
    divSideBarContents.setAttribute('id', 'sideBarContents');
    divSideBar.appendChild(divSideBarContents);
    //创建自定义列表
    var dlist = document.createElement("dl");
    divSideBarContents.appendChild(dlist);
    var num = 0;//统计找到的mt和st
    mt = mt.toUpperCase();//转化成大写
    st = st.toUpperCase();//转化成大写
    //遍历所有元素结点
    for (var i = 0; i < nodes.length; i++) {
        if (nodes[i].nodeName == mt || nodes[i].nodeName == st) {
            //获取标题文本
            var nodetext = nodes[i].innerHTML.replace(/<\/?[^>]+>/g, "");//innerHTML里面的内容可能有HTML标签，所以用正则表达式去除HTML的标签
            nodetext = nodetext.replace(/ /ig, "");//替换掉所有的
            nodetext = htmlDecode(nodetext);
            //插入锚
            nodes[i].setAttribute("id", "blogTitle" + num);
            var item;
            switch (nodes[i].nodeName) {
                case mt:    //若为主标题
                    item = document.createElement("dt");
                    break;
                case st:    //若为子标题
                    item = document.createElement("dd");
                    break;
            }
            //创建锚链接
            var itemtext = document.createTextNode(nodetext);
            item.appendChild(itemtext);
            item.setAttribute("name", num);
            item.onclick = function () {        //添加鼠标点击触发函数
                var pos = getElementPosition(document.getElementById("blogTitle" + this.getAttribute("name")));
                if (!moveScrollBar(pos.top, interval)) return false;
            };
            //将自定义表项加入自定义列表中
            dlist.appendChild(item);
            num++;
        }
    }
    if (num == 0) return false;
    /*鼠标进入时的事件处理*/
    divSideBarTab.onmouseenter = function () {
        divSideBarContents.style.display = 'block';
    }
    /*鼠标离开时的事件处理*/
    divSideBar.onmouseleave = function () {
        divSideBarContents.style.display = 'none';
    }

    document.body.appendChild(divSideBar);
};

function htmlDecode(text) {
    var temp = document.createElement("div");
    temp.innerHTML = text;
    var output = temp.innerText || temp.textContent;
    temp = null;
    return output;
};

function getElementPosition(ele) {
    var topPosition = 0;
    var leftPosition = 0;
    while (ele) {
        topPosition += ele.offsetTop;
        leftPosition += ele.offsetLeft;
        ele = ele.offsetParent;
    }
    return {top: topPosition, left: leftPosition};
};

/*
    移动滚动条，finalPos 为目的位置，internal 为移动速度
*/
function moveScrollBar(finalpos, interval) {
    //若不支持此方法，则退出
    if (!window.scrollTo) {
        return false;
    }
    //窗体滚动时，禁用鼠标滚轮
    window.onmousewheel = function () {
        return false;
    };
    //清除计时
    if (document.body.movement) {
        clearTimeout(document.body.movement);
    }

    var currentpos = getScrollBarPosition();//获取滚动条当前位置

    var dist = 0;
    if (currentpos == finalpos) {//到达预定位置，则解禁鼠标滚轮，并退出
        window.onmousewheel = function () {
            return true;
        }
        return true;
    }
    if (currentpos < finalpos) {//未到达，则计算下一步所要移动的距离
        dist = Math.ceil((finalpos - currentpos) / 10);
        currentpos += dist;
    }
    if (currentpos > finalpos) {
        dist = Math.ceil((currentpos - finalpos) / 10);
        currentpos -= dist;
    }
    var scrTop = getScrollBarPosition();//获取滚动条当前位置
    window.scrollTo(0, currentpos);//移动窗口
    if (getScrollBarPosition() == scrTop)//若已到底部，则解禁鼠标滚轮，并退出
    {
        window.onmousewheel = function () {
            return true;
        }
        return true;
    }
    //进行下一步移动
    var repeat = "moveScrollBar(" + finalpos + "," + interval + ")";
    document.body.movement = setTimeout(repeat, interval);
};

/**
 * 获取滚动条当前位置
 * @returns {number}
 */
function getScrollBarPosition() {
    var scrollBarPosition = document.body.scrollTop || document.documentElement.scrollTop;
    return scrollBarPosition;
};
