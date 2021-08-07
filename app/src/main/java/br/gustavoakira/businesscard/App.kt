package br.gustavoakira.businesscard

import android.app.Application
import br.gustavoakira.businesscard.data.AppDatabase
import br.gustavoakira.businesscard.data.BusinessCardRepository

class App: Application(){
    val database by lazy{AppDatabase.getDatabase(this)}
    val repository by lazy { BusinessCardRepository(database.businessDao()) }
}