package br.gustavoakira.businesscard.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import br.gustavoakira.businesscard.App
import br.gustavoakira.businesscard.R
import br.gustavoakira.businesscard.databinding.ActivityMainBinding
import br.gustavoakira.businesscard.util.Image

class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as App).repository)
    }

    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rcView.adapter = adapter
        getAllBusinessCar()
        insertListener()
    }

    private fun insertListener(){
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity,AddBusinessCardActivity::class.java)
            startActivity(intent)
        }
        adapter.listenerShare = {card->
            Image.share(this@MainActivity, card)
        }
    }

    private fun getAllBusinessCar(){
        mainViewModel.getAll().observe(this,{businessCards->
            adapter.submitList(businessCards)
        })
    }
}