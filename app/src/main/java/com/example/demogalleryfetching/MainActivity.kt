package com.example.demogalleryfetching

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

const val MY_SELECT_FILE = 1

class MainActivity : AppCompatActivity() {
	private val items = ArrayList<Item>()
	private val mAdapter = ItemAdapter(items)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		recyclerView.apply {
			adapter = mAdapter
			itemAnimator = DefaultItemAnimator()
		}

		buttonLoadImage.setOnClickListener {
			val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
			galleryIntent.apply {
				type = "image/*"
			}
			startActivityForResult(galleryIntent, MY_SELECT_FILE)
		}
		val callback = DragManagerAdapter(
				this, mAdapter,
				ItemTouchHelper.UP.or(ItemTouchHelper.DOWN),
				ItemTouchHelper.LEFT.or(ItemTouchHelper.RIGHT)
		)
		val helper = ItemTouchHelper(callback)
		helper.attachToRecyclerView(recyclerView)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == RESULT_OK && requestCode == MY_SELECT_FILE) {
			val imageUri = data?.data as Uri
			MyAsyncTask(imageUri).execute()
		}
	}

	inner class MyAsyncTask(private val image: Uri) : AsyncTask<Void, Uri, String>() {
		override fun doInBackground(vararg params: Void): String? {
			publishProgress(image)
			return ""
		}

		override fun onProgressUpdate(vararg images: Uri?) {
			mAdapter.addItem(items.size, Item(image))
		}

		override fun onPostExecute(result: String?) {
			Toast.makeText(this@MainActivity, getString(R.string.msg_done), Toast.LENGTH_SHORT).show()
		}
	}
}
