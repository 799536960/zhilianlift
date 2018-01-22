# 智联生活

## 计划
* 商城 `24天`
    * ~~首页(全部动态展示 后台替换)~~ `2天` 
    * ~~城市切换(新的布局)~~`1天`
    * ~~首页2级3级分类~~`1天`
    * ~~搜索(包括热门搜索 历史记录)~~`0.5天`
    * ~~商品列表(筛选.排序.多布局)~~`1.5天`
    * ~~商品详情(看是否原生 最好原生 因为购物车有原生的规格选择)~~ `3天`
    * ~~确认订单页面~~`1天`
    * ~~地址库管理页面`1天`->(因为有仿京东的城市选择器)~~`3天`
    * ~~第一方支付页面(app内部支付 需要和支付宝一样 有支付密码 管理支付密码)~~`1天`
    * ~~购物车页面(可以选择规格)~~`2天`
    * ~~全部订单列表页面~~`1天`
    * ~~订单详情页面~~`1天`
    * ~~订单跟踪页面(就是查看物流)~~`1天`
    * ~~多商品评价列表页面~~`1天`
    * ~~申请退款页面(没有发货只有申请退款)~~`1天`
    * ~~申请售后页面(发货后 可以退货和换货)~~`1天`
    * 我的钱包功能(包括:装修资金页面 优惠券页面 余额页面  补贴积分页面 充值页面 提现页面 等)`2天`
    * 第三方支付页面(收银台)`1天`
    * 扫码支付页面(和支付宝付款码类似 app提供扫码功能)`1天`
 * 住房 `11天`
    * 找新房
        * 报备页面`1天`
    * 二手房
        * ~~二手房上下架列表页面~~`1天`
        * ~~二手房编辑页面~~`1天`
        * ~~添加二手房页面(包括添加成功页面)~~`1天`
    * 租房
        * ~~租房上下架列表页面~~`1天`
        * ~~租房编辑页面~~`1天`
        * ~~添加租房页面(包括添加成功页面)(和二手房功能一样 但是页面不一样)~~`1天`
    * 找新房/二手房/租房列表页(包括定位切换区县 筛选 页面搜索 排序 区域选择 )`2天`
    * 找新房/二手房/租房详情页(三个类型 功能一样 但是页面不一样 只有部分布局可以共用)`2天`
 * 金融 `3天`
    * 金融首页规则方案`1天`
    * 贷款申请页面`1天`
    * 身份证/房产证上传审核页面 提交成功页面`1天`
 * 个人中心 `12天`
    * ~~个人中心首页(个人信息验证 消息提醒 订单角标 钱包资金展示)~~`1天`
    * ~~消息中心功能 包括清空 查看~~`1天`
    * 账户设置 
        * ~~个人资料编辑(头像 昵称等 都是跳转单个页面和美业 子母扣不一样)~~`1.5天`
        * 实名认证功能 
            * ~~实名认证首页(展示认证情况)~~`0.5天`
            * ~~验证绑定手机页面(验证验证码)~~`0.5天`
            * ~~实名认证页面 上传多个类型身份证照片~~`0.5天`
            * ~~提交成功页面/实名认证成功页面~~ `0.5天`
        * 账户与安全 
            * ~~账户安全菜单页面/修改手机号码页面/修改登录密码页面/展示支付密码设置状态页面~~`1.5天`
            * ~~设置支付密码页面(和支付宝类似 需要自定义布局)~~`1天`
            * ~~支付密码管理页面/设置成功页面~~`0.5天`
            * ~~修改支付密码页面~~`1天`
            * ~~忘记支付密码页面~~`1天`
        *  ~~意见反馈/关于页面/清除缓存/退出登录页面~~`0.5天`
    * ~~我的收藏页面 包括批量删除 和查看~~ `0.5天`
    * ~~浏览记录 包括清空 编辑 批量删除功能~~ `0.5天`
 * ~~登录注册~~ `2天`
    * ~~登录 注册 防盗刷校验 验证码~~ `1.5天`
    * ~~忘记密码 防盗刷校验 验证码~~ `0.5天`
## 总计52工作日 已经做了首页分类 所以是50工作日 中间不计算放假 只计算双休 从今天12月5号刚好到2月12号

## 1.0版本
没有分页 有下拉刷新

进去 下拉loading 错误 下拉loading 正确 下拉 下拉loading



### 11.7
1. 分析项目流程,创建框架
1. 使用组件式框架,使公共部分分离(以后可以重用)
1. 创建base文件 提取部分功能
1. 导入设配最新Android 8.1 sdk和兼容包 

### 11.21
1. 添加loading error 和 refresh

### 11.29
1. 添加百度地图和百度定位并且封装完成
1. 搭建首页fragment
1. 添加homefragment数据
1. 添加首页页面
1. 添加定位权限控制

### 12.4
1. 添加首页接口和数据
1. 添加分类页面
1. 接入分类数据 完成分类页面功能
1. 修复分类快速点击崩溃的问题
1. 添加城市选择页面
1. 添加城市搜索功能
1. 添加部分特殊接口页面的缓存
1. 添加搜索页面
1. 添加搜索页面热搜 和 历史搜索 和相关缓存设置
1. 解决了loading页面不能显示topbar的问题
1. 添加注册页面和注册功能
1. 添加获取验证码和防盗刷页面
1. 添加完成 登陆/忘记密码 功能

### 12.11
1. 添加个人中心页面
1. 添加设置页面
1. 添加个人中心所有数据和页面角标
1. 添加修改个人数据功能(头像 昵称等 )
1. 优化base代码 地址选择遇到问题 已解决
1. 添加仿京东地址选择器 花费了2天
1. 添加收货地址列表
1. 添加收货地址和编辑收货地址页面功能
1. 添加设置默认收货地址和删除收货地址功能
1. 添加验证手机号码功能
1. 添加身份证提交
1. 添加实名认证提交页面
1. 添加实名认证成功 和 编辑页面
1. 添加意见反馈功能
1. 添加关于和清除缓存功能

### 12.18
1. 优化首页列表 现在首页刷新数据不在占用cpu
1. 添加账号与安全设置页面
1. 添加完成修改密码功能和页面
1. 添加完成更换手机功能
1. 添加支付页面自定义输入框
1. 添加支付输入密码页面
1. 添加完成支付状态页面和支付设置页面和功能
1. 添加自定义密码输入框 和支付宝类似
1. 添加设置支付密码页面
1. 添加支付密码管理页面/设置成功页面
1. 添加修改支付密码页面
1. 添加忘记支付密码页面
1. 修改城市选择器中的code参数
1. 添加定位逆地理编码(不然获取不到区划代码)

### 12.25
1. 添加商品列表页条件排序页面
1. 添加商品列表数据功能和切换列表和网格布局
1. 添加商品列表的区域查询
1. 添加商品列表条件筛选(后台可控制的)
1. 添加商品详情首页tab切换布局
1. 添加新的banner控件
1. 添加商品详情首页数据和评论
1. 添加商品展示的详情页 解决了webview显示详情图片大小不能沾满全屏的问题
1. 添加评论top筛选菜单页面
1. 添加评论列表功能 页面
1. 添加图片详情页(图片点击进入图片详情页)
1. 添加商品详情页跳转评价详情页面
1. 添加下拉跳转详情页(有隐患)
1. 添加类型选择dialog
1. 添加类型选择dialog的筛选数据和自适应布局
1. 添加规格选择逻辑 拼接多语句
1. 修改规格dialog的逻辑 现在和淘宝选择规格一样了
1. 添加商品在首页和dialog里加入购物车功能
1. 添加商品收藏和取消收藏功能
1. 添加商品详情pop菜单功能页面
1. 添加pop消息角标
1. 添加购物车fragment页面布局
1. 添加购物车rv的列表显示和接口
1. 添加购物车全选和单个选中功能和接口
1. 添加购物车编辑和完成切换功能

### 1.2
1. 添加修改规格 刷新页面功能
1. 添加修改商品数量和修改商品数量弹窗功能
1. 添加新的购物车页面用于商品详情页面跳转
1. 修改购物车一些bug 购物车功能完成
1. 添加我的收藏页面
1. 添加我的记录页面
1. 添加编辑收藏 和 记录的功能
1. 添加完成我的收藏和记录页面功能
1. 修改首页ui 添加沉浸式状态栏
1. 添加确认订单页
1. 完善确认订单页
1. 添加优惠券列表
1. 添加确认订单选择优惠券列表
1. 添加输入支付密码单端页面 并分离fragment 
1. 添加输入支付密码 和 登录的开场动画


### 1.8
1. 添加完成了自定义的支付密码和输入框
1. 把一起的支付密码输入替换为自定义的 更加安全
1. 完成了支付密码输入页面
1. 完成了确认订单页面
1. 修复了查看图片可能引起崩溃的问题
1. 添加收银台功能(没有接第三方支付)
1. 添加支付成功页面
1. 添加订单列表页
1. 添加我的房产中的页面框架
1. 添加订单列表页
1. 完善订单列表页 添加订单多种状态列表
1. 添加订单详情页 添加订单详情多少状态
1. 订单详情所有接口完成
1. 添加webview的框架 添加完成查看物流
1. 完成 删除订单 确认收货 取消订单 再次购买 去付款
1. 添加评价及其多图上传
1. 完成评价订单所有功能
1. 添加申请售后列表 和 跳转各个页面
    
### 1.15
1. 添加完成 添加申请售后 所有功能
1. 添加在申请中的列表
1. 添加售后详情
1. 添加消息中心 删除 清空 和列表显示
1. (activity finish 和 http 取消tag的问题) 已解决
1. 添加积分明细 余额明细 装修资金明细
1. 添加租房和售房编辑页面
1. 完成租房 售房编辑上传功能
1. 添加完成我的房产列表 功能
1. 修复了一个选择城市闪退的问题
1. 添加二手房 和 租房的列表页面
1. 完成二手房 租房的筛选
1. 完成二手房 和 租房的价格单选和户型单选

### 1.22
1. 因为住房取消物业功能 所有逻辑和ui更改
1. 添加完成二手房所有功能
1. 添加完成租房所有功能

    
    
   
    
    
    
     