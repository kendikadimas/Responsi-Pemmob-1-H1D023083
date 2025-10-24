# Responsi-1
# Dimas Kendika Fazrulfalah - H1D023083
# Shift Asal : A
# Shift Baru : C

# ScreenRecord
![]

### ALUR API
### Komponen Utama

* **API Client** (`ApiClient.kt`, `ApiInterface.kt`): Koneksi dengan server untuk mengambil data dengan Retrofit dan nanti data diterima dalam format JSON
* **Model Data** (`SearchResponse.kt`): Mengubah JSON menjadi Objek yang bisa dipakai Kotlin
* **ViewModel** (`MainViewModel.kt`): Meminta data ke API Client, menyimpan data memakai LiveData, dan memberi tahu Activity kalau data sudah jadi atau ada error
* **Activity Utama** (`MainActivity.kt`): Didalam MainActivity terdapat penerapan MainViewModel yang nantinya akan mengambil data dan mendengarkan live data dari View Model. Setelah mendapat data, ia akan menyimpan sementara dan mengirimkan ke Activity lain saat klik tombol/card di menu
* **Activity Lain** (`CoachActivity.kt`, `PlayerActivity.kt`): Menampilkan data detail pelatih atau daftar pemain. Menerima data dari MainActivity lewat Intent.
* **Adapter** (`PlayerAdapter.kt`): Mengambil data list pemain dan mengatur setiap data agar dapat ditampilkan dalam sebuah RecyclerView, dan juga mengatur warna kartu. Adapter juga memberi tahu PlayerAct jika ada card pemain yang diklik
* **Fragment** (`PlayerDetailFragment.kt`): Popup yang muncul di PlayerActivity saat card pemain diklik. Ia menerima data detail 1 pemain dari PlayerActivity dan menampilkannya.

### Alur Kerja Langkah-demi-Langkah

1.  Pengguna membuka aplikasi, `MainActivity` tampil.
2.  `MainActivity` memerintahkan `MainViewModel` untuk mengambil data tim (`fetchTeamData`).
3.  `MainViewModel` menggunakan `ApiClient` (Retrofit) untuk menghubungi URL API (misal: `https://api.football-data.org/v4/teams/100`).
4.  API mengirim balik data dalam format JSON.
5.  Retrofit & GSON mengubah JSON ini menjadi objek `SearchResponse`.
6.  `MainViewModel` menyimpan objek `SearchResponse` ini di `LiveData` bernama `_teamData` dan memberi tahu `MainActivity` bahwa data baru telah tiba.
7.  `MainActivity` yang `observe` `LiveData` tersebut, menerima data `SearchResponse`. Ia lalu mengambil data pelatih (`.coach`) dan daftar pemain (`.squad`) dan menyimpannya di variabel lokal (`currentCoach` dan `currentSquad`).
8.  Aplikasi menunggu pengguna menekan tombol menu (Coach atau Team).

    * **Jika Tombol Coach Diklik:**
        * `MainActivity` membuat `Intent` ke `CoachActivity`.
        * Data pelatih (`currentCoach`) diberikan ke `Intent` tersebut (`putExtra`).
        * `CoachActivity` dimuat
        * `CoachActivity` mengambil data pelatih dari `Intent` (`getSerializableExtra`) dan menampilkannya di `TextView`.

    * **Jika Tombol Team Diklik:**
        * `MainActivity` membuat `Intent` ke `PlayerActivity`.
        * Data daftar pemain (`currentSquad`) diberikan ke `Intent`.
        * `PlayerActivity` dimuat
        * `PlayerActivity` mengambil daftar pemain dari `Intent`.
        * `PlayerActivity` membuat `PlayerAdapter`, memberinya daftar pemain tadi, dan memasangnya ke `RecyclerView`.
        * `RecyclerView` & `PlayerAdapter` bekerja sama menampilkan kartu-kartu pemain beserta warna sesuai posisi.

9.  **Tampilkan Detail Pemain:**
    * Pengguna mengklik salah satu kartu pemain di `PlayerActivity`.
    * `PlayerAdapter` mendeteksi klik ini dan memanggil fungsi `onPlayerClick` di `PlayerActivity`, sambil memberikan data pemain yang diklik.
    * `PlayerActivity` membuat `PlayerDetailFragment` baru, membungkus data pemain tadi ke dalam `Bundle` (menggunakan `newInstance`), dan menyuruh `FragmentManager` untuk menampilkan Fragment tersebut di wadah (`FrameLayout`).
    * `PlayerDetailFragment` mengambil data pemain dari `Bundle` (`arguments`) dan menampilkannya di `TextView` di dalam Fragment.

10. Pengguna menekan tombol back atau mengetuk area Fragment, `FragmentManager` menutup `PlayerDetailFragment` dan kembali menampilkan daftar pemain di `PlayerActivity`.
