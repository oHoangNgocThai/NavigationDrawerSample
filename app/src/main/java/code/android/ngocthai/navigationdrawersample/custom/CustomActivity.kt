package code.android.ngocthai.navigationdrawersample.custom

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import code.android.ngocthai.navigationdrawersample.R
import code.android.ngocthai.navigationdrawersample.entity.ItemCustom
import kotlinx.android.synthetic.main.activity_custom.*

class CustomActivity : AppCompatActivity(), CustomItemAdapter.ItemCustomListener {

    private val customAdapter = CustomItemAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        recyclerCustom.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        }

        val listItem = createData()
        customAdapter.updateAllData(listItem)
    }

    override fun onItemClicked(item: ItemCustom) {
        Toast.makeText(applicationContext, "Click item: ${item.title}", Toast.LENGTH_SHORT).show()
    }

    private fun createData(): List<ItemCustom> {
        val items = mutableListOf<ItemCustom>()
        items.add(ItemCustom(R.drawable.ic_home_black_24dp, "Home", 3))
        items.add(ItemCustom(R.drawable.ic_favorite_black_24dp, "Favorite", 0))
        items.add(ItemCustom(R.drawable.ic_search_black_24dp, "Search", 4))
        return items
    }
}
