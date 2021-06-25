package application;

public class ekleKayit {

	private int barkod_No;
	private String urun_Ad;
	private int fiyat;
	private String tarih_Giris;
	private String urun_Turu;

	ekleKayit() {

	}

	public ekleKayit(int barkod_No, String urun_Turu, String urun_Ad, int fiyat, String tarih_Giris) {

		this.barkod_No = barkod_No;
		this.urun_Turu = urun_Turu;
		this.urun_Ad = urun_Ad;
		this.fiyat = fiyat;
		this.tarih_Giris = tarih_Giris;
	}

	public int getBarkod_No() {
		return barkod_No;
	}

	public void setBarkod_No(int barkod_No) {
		this.barkod_No = barkod_No;
	}

	public String getUrun_Turu() {
		return urun_Turu;
	}

	public void setUrun_Turu(String urun_Turu) {
		this.urun_Turu = urun_Turu;
	}

	public String getUrun_Ad() {
		return urun_Ad;
	}

	public void setUrun_Ad(String urun_Ad) {
		this.urun_Ad = urun_Ad;
	}

	public int getFiyat() {
		return fiyat;
	}

	public void setFiyat(int fiyat) {
		this.fiyat = fiyat;
	}

	public String getTarih_Giris() {
		return tarih_Giris;
	}

	public void setTarih_Giris(String tarih_Giris) {
		this.tarih_Giris = tarih_Giris;
	}

}