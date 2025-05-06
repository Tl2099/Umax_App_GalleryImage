package tl209.umax_app_galleryimage

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module
import tl209.umax_app_galleryimage.model.AppDatabase
import tl209.umax_app_galleryimage.viewmodel.LikedImageViewModel

val appModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "gallery-db"
        ).build()
    }

    single { get<AppDatabase>().likedImageDao() }

    viewModel { LikedImageViewModel(get()) }
}
