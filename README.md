# NavigationDrawerSample

# Overview

* Là bảng giao diện người dùng hiển thị menu điều hướng chính của ứng dụng. Nó xuất hiện khi người dùng vuốt ngón tay từ cạnh trái của màn hình hoặc là drawer icon.

* Drawer icon được hiển thị ở top-level của các destination sử dụng **DrawerLayout**. Không hiển thị nút **Up** trong thanh ứng dụng.

# Create Navigation Drawer

* Để thêm một navigation drawer, hãy khai báo **DrawerLayout** làm root view. Bên trong nó chứa nội dung chính của UI và nội dung của DrawerLayout được hiển thị qua **NavigationView**.

```
<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    tools:openDrawer="start">

    <include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <android.support.design.widget.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:fitsSystemWindows="true"
        app:headerLayout="@layout/nav_header_main"
        app:menu="@menu/activity_main_drawer" />

</android.support.v4.widget.DrawerLayout>
```

* NavigationView có 2 thành phần thêm vào là header và menu. Header là phần giao diện bạn tự custom còn menu là các icon và label được hiển thị trong NavigationView.

* Như mô tả ở trên, nếu sử dụng icon drawer để hiển thị thì nên sử dụng **ActionBarDrawerToggle** để cài đặt cùng drawerLayout. 

```
val toggle = ActionBarDrawerToggle(
    this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
)
drawer_layout.addDrawerListener(toggle)
toggle.syncState()
```

* Muốn bắt sự kiện click vào menu bên trong NavigationView thì sử dụng listener **onNavigationItemSelected**.

```
override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
        R.id.nav_camera -> {
        }
        ...

    }
    drawer_layout.closeDrawer(GravityCompat.START)
    return true
}
```
* Để kiểm tra xem drawer có đang hiển thị không, sử dụng phương thức **isDrawerOpen**.

```
drawer_layout.isDrawerOpen(GravityCompat.START)
```

# Navigation Drawer with Navigation Component

* Để cài đặt Navigation Drawer với Navigation Component, trước hết phải tạo 1 fragment để chứa được các destination của Navigation Component ở trong Activity.

```
<fragment
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:defaultNavHost="true"
    app:navGraph="@navigation/nav_component" />
```

* Tiếp theo kết nối DrawerLayout với navigation graph sử dụng **AppBarConfiguration**. 

```
val appBarConfiguration = AppBarConfiguration(nav.graph, drawer_layout)
```

> Khi sử dụng `NavigationUI`, top bar sẽ tự động chuyển giữa drawer icon và Up icon khi thay đổi các destination. Vì vậy bạn không cần phải sử dụng `ActionBarDrawerToggle`.

* Tiếp đến, setup NavigationView với Navigation Component sử dụng phương thức `setupWithNavController`.

```
val nav = findNavController(this, R.id.nav_host_fragment)
nav_view?.setupWithNavController(nav)
```

* Override thêm phương thức **onSupportNavigateUp()** và sử dụng cho **AppBarConfiguration**.

```
override fun onSupportNavigateUp(): Boolean {
    return findNavController(R.id.nav_host_fragment).navigateUp(appBarConfiguration)
}
```

* Vẫn có một chú ý là các id của fragment trong navigation graph phải trùng với id của menu của NavigationView.



# Custom Navigation Drawer

> Có thể sử dụng custom navigation drawer khi muốn giao diện hiển thị khác với mặc định, chẳng hạn như là một list các item với nhiều thông tin hơn chứ không đơn giản chỉ là icon và text, cập nhật nếu có thông báo trong từng mục lên giao diện, ...

## Custom List Item

* Chúng ta hãy thử tạo một list các item thay thế cho menu của Navigation Drawer.
