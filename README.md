# SuperStateView
控件为android平台，多状态布局控件支持状态有以下5钟：  
"sup_empty"  EMPTY = 2; //空状态   
"sup_load"    LOADING = 0;//加载  
"sup_error"   ERROR = 4; //发生错误  
"sup_content"   COTENT = 1; //内容  
"sup_unnet"     NETERROR = 3; //网络错误  
使用方法为：  
    <com.ly.SuperStateView  
        android:id="@+id/spsv_main"  
        android:layout_width="match_parent"  
        android:layout_height="match_parent"  
        app:sup_load="@layout/common_load"  
        app:sup_content="@layout/sup_content"  
       />  
代码里面提供两种切换状态方式：  
 （1） showLoadingView（）  
      showEmptyView（）  
      showContentView()  
      showErrorView()  
      showNetErrorView()  
 （2）showView(int type)  
 建议在activity 或者fragment ondestroy方法里面调用，superstateview的ondestroy方法，释放内存。  
 使用方法：
 (1)jitpack
 Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.lyxxman:SuperStateView:1.0.0'
	}
(2)jcenter
 implementation 'com.ly:superstateview:1.0.0'
 
 
 
