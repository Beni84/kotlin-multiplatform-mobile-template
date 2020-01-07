package org.example.kotlin.multiplatform.app

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gigigo.baserecycleradapter.adapter.BaseRecyclerAdapter
import com.google.android.material.snackbar.Snackbar
import org.example.kotlin.multiplatform.entities.Contact
import org.example.kotlin.multiplatform.presenter.MainPresenter
import org.example.kotlin.multiplatform.presenter.MainView

class MainActivity : AppCompatActivity(), MainView {

    private var baseAdapter: BaseRecyclerAdapter<Contact>? = null
    private var presenter: MainPresenter? = null
    private var recyclerview: RecyclerView? = null
    private var progress: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initRecycler()

        presenter = app.notDagger.helloPresenter(this)
    }

    private fun initViews() {
        recyclerview = findViewById(R.id.recyclerview)
        progress = findViewById(R.id.progress)
    }

    private fun initRecycler() {
        recyclerview?.apply {
            baseAdapter = BaseRecyclerAdapter(applicationContext)
            baseAdapter?.bind<Contact, ContactViewHolder>()

            adapter = baseAdapter
            layoutManager = GridLayoutManager(applicationContext, 2)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter?.loadContacts()
    }

    override fun showContacts(contactList: List<Contact>) {
        baseAdapter?.addAll(contactList)
    }

    override fun showLoading(visible: Boolean) {
        progress?.visibility = if (visible) VISIBLE else GONE
    }

    override fun showError(error: String) {
        Snackbar.make(findViewById(R.id.rootView), error, Snackbar.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        presenter?.destroy()
        super.onDestroy()
    }
}
