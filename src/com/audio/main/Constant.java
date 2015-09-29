package com.audio.main;

//mpintro = MediaPlayer.create(this, Uri.parse(Environment.getExternalStorageDirectory().getPath()+ "/Music/intro.mp3"));
//mpintro.setLooping(true);
//mpintro.start();
public class Constant {
	public static final Integer[] images = { R.drawable.number_but1, R.drawable.number_but2};
	public static String[] total_url = {
		"001 AL FATIHA_0.mp3",
		"002 AL BAQARA.mp3",
		"003 ALI IMRAN.mp3",
		"004 AN NISAA.mp3",
		"005 AL MAIDA.mp3",
		"006 AL ANAAM.mp3",
		"007 AL AARAF.mp3",
		"008 AL ANFAL.mp3",
		"009 AT TAUBA.mp3",
		"010 YUNS.mp3",
		"011 HOOD.mp3",
		"012 YUSF.mp3",
		"013 AR RAAD.mp3",
		"014 IBRAHIM.mp3",
		"015 AL HIJR.mp3",
		"016 AN NAHL.mp3",
		"017 AL ISRAA.mp3",
		"018 AL KAHF.mp3",
		"019 MARYAM.mp3",
		"020 TTAHA.mp3",
		"021 AL ANBYAA.mp3",
		"022 AL HAJ.mp3",
		"023 AL MUMINUN.mp3",
		"024 AN NOOR.mp3",
		"025 AL FURQAN.mp3",
		"026 ASH SHUARAA.mp3",
		"027 AN NAML.mp3",
		"028 AL QASAS.mp3",
		"029 AL ANKABOOT.mp3",
		"030 AR ROOM.mp3",
		"031 LUQMAN.mp3",
		"032 AS SAJDA.mp3",
		"033 AL AHZAB.mp3",
		"034 SABAA.mp3",
		"035 FATIR.mp3",
		"036 YASEEN.mp3",
		"037 AS SAFAT.mp3",
		"038 SSAD.mp3",
		"039 AZ ZUMAR.mp3",
		"040 GHAFIR.mp3",
		"041 FUSSILAT.mp3",
		"042 ASH SHURA.mp3",
		"043 AZ ZUKHRUF.mp3",
		"044 AD DUKHAN.mp3",
		"045 AL JATHIA.mp3",
		"046 AL AHQAF.mp3",
		"047 MUHAMAD.mp3",
		"048 AL FATH.mp3",
		"049 AL HUJURAT.mp3",
		"050 QAAF.mp3",
		"051 AZH ZHARIAT.mp3",
		"052 AT TTOOR.mp3",
		"053 AN NAJM.mp3",
		"054 AL QAMAR.mp3",
		"055 AR RAHMAN.mp3",
		"056 AL WAQEA.mp3",
		"057 AL HADEED.mp3",
		"058 AL MUJADILA.mp3",
		"059 AL HASHR.mp3",
		"060 AL MUMTAHINA.mp3",
		"061 AS SSAF.mp3",
		"062 AL JUMMA.mp3",
		"063 AL MUNAFIQOON.mp3",
		"064 AT TAGHABUN.mp3",
		"065 AT TTALAQ.mp3",
		"066 AT TAHREEM.mp3",
		"067 al MULK.mp3",
		"068 AL QALAM.mp3",
		"069 AL HAQA.mp3",
		"070 AL MAARIJ.mp3",
		"071 NOOH.mp3",
		"072 AL JIN.mp3",
		"073 AL MUZAMIL.mp3",
		"074 AL MUDDATHIR.mp3",
		"075 ALQIAMA.mp3",
		"076 AL INSAN.mp3",
		"077 AL MURSALAT.mp3",
		"078 AN NABAA.mp3",
		"079 AN NAZIAT.mp3",
		"080 ABASA.mp3",
		"081 AT TAKWEER.mp3",
		"082 AL INFITAR.mp3",
		"083 AL MUTAFFIN.mp3",
		"084 AL INSHIQAQ.mp3",
		"085 AL BUROOJ.mp3",
		"086 AT TTARIQ.mp3",
		"087 AL AALA.mp3",
		"088 AL GHASHIA.mp3",
		"089 AL FAJR.mp3",
		"090 AL BALAD.mp3",
		"091 ASH SHAMS.mp3",
		"092 AL LAIL.mp3",
		"093 AZH ZHUHA.mp3",
		"094 ASH SHARH.mp3",
		"095 AT TEEN.mp3",
		"096 AL ALAQ.mp3",
		"097 AL QADR.mp3",
		"098 AL BAENA.mp3",
		"099 AZ ZALZALA.mp3",
		"100 AL ADIAT.mp3",
		"101 AL QAREA.mp3",
		"102 AT TAKATHUR.mp3",
		"103 AL ASSR.mp3",
		"104 AL HUMAZA.mp3",
		"105 AL FEEL.mp3",
		"106 QURAISH.mp3",
		"107 AL MAAUN.mp3",
		"108 AL KAUTHAR.mp3",
		"109 AL KAFIROON.mp3",
		"110 AN NASSR.mp3",
		"111 AL MASAD.mp3",
		"112 AL IKHLASS.mp3",
		"113 AL FALAQ.mp3",
		"114 AN NAS.mp3"
	};
	public static String[] i_title = {
			"سورة الفاتحة",
			"سورة البقرة", 
			"سورة آل عمران",
			"سورة النساء", 
			"سورة المائدة",
			"سورة الأنعام", 
			"سورة الأعراف", 
			"سورة الأنفال", 
			"سورة التوبة", 
			"سورة يونس", 
			"سورة هود", 
			"سورة يوسف", 
			"سورة الرّعد", 
			"سورة إبراهيم", 
			"سورة الحجر", 
			"سورة النحل", 
			"سورة الإسراء", 
			"سورة الكهف", 
			"سورة مريم", 
			"سورة طه", 
			"سورة الأنبياء", 
			"سورة الحج", 
			"سورة المؤمنون", 
			"سورة النّور", 
			"سورة الفرقان",
			"سورة الشعراء", 
			"سورة النمل", 
			"سورة القصص", 
			"سورة العنكبوت", 
			"سورة الروم", 
			"سورة لقمان", 
			"سورة السجدة", 
			"سورة الأحزاب", 
			"سورة سبأ",
			"سورة فاطر", 
			"سورة يس",
			"سورة الصّافّات", 
			"سورة ص",
			"سورة الزمر",
			"سورة غافر", 
			"سورة فصّلت", 
			"سورة الشورى", 
			"سورة الزخرف", 
			"سورة الدخان", 
			"سورة الجاثية", 
			"سورة الأحقاف", 
			"سورة محمّـد", 
			"سورة الفتح", 
			"سورة الحُـجُـرات",
			"سورة ق", 
			"سورة الذاريات", 
			"سورة الـطور", 
			"سورة النجم", 
			"سورة الـقمـر", 
			"سورة الـرحـمـن", 
			"سورة الواقعة",
			"سورة الحـديد", 
			"سورة الـمجادلـة", 
			"سورة الـحـشـر", 
			"سورة الـمـمـتـحنة", 
			"سورة الـصّـف", 
			"سورة الـجـمـعـة", 
			"سورة الـمنافقون", 
			"سورة الـتغابن", 
			"سورة الـطلاق", 
			"سورة الـتحريم", 
			"سورة الـملك", 
			"سورة الـقـلـم", 
			"سورة الـحاقّـة", 
			"سورة الـمعارج", 
			"سورة نوح", 
			"سورة الجن", 
			"سورة الـمـزّمّـل", 
			"سورة الـمّـدّثّـر", 
			"سورة الـقـيامـة", 
			"سورة الإنسان", 
			"سورة الـمرسلات", 
			"سورة النبأ",
			"سورة الـنازعات", 
			"سورة عبس", 
			"سورة التكوير",
			"سورة الانفطار", 
			"سورة المطـفـفين", 
			"سورة الانشقاق", 
			"سورة البروج", 
			"سورة الـطارق", 
			"سورة الأعـلى", 
			"سورة الغاشـيـة", 
			"سورة الفجر", 
			"سورة الـبلد", 
			"سورة الـشـمـس", 
			"سورة اللـيـل", 
			"سورة الضـحى", 
			"سورة الـشرح", 
			"سورة الـتين", 
			"سورة الـعلق", 
			"سورة الـقدر", 
			"سورة الـبينة", 
			"سورة الـزلزلة", 
			"سورة الـعاديات", 
			"سورة الـقارعـة", 
			"سورة الـتكاثر", 
			"سورة الـعصر", 
			"سورة الـهمزة", 
			"سورة الـفيل", 
			"سورة قريش", 
			"سورة المـاعون", 
			"سورة الـكوثر", 
			"سورة الـكافرون", 
			"سورة الـنصر", 
			"سورة الـمسد", 
			"سورة الإخلاص", 
			"سورة الـفلق", 
			"سورة الـناس"


	};
	public static String[] e_title = {
			"Surat Al Fatihah",
			"Surat Al Baqarah",	
			"Surat Al 'Imran", 
			"Surat An Nisa\"", 
			"Surat Al Ma\"idah", 
			"Surat Al An'am", 
			"Surat Al A'raf", 
			"Surat Al Anfal", 
			"Surat At Tawbah", 
			"Surat Yunus", 
			"Surat Hud", 
			"Surat Yusuf", 
			"Surat Ar Ra'd", 
			"Surat Ibrahim", 
			"Surat Al Hijr", 
			"Surat An Nahl", 
			"Surat Al Isra\"", 
			"Surat Al Kahf", 
			"Surat Maryam", 
			"Surat Ta Ha", 
			"Surat Al Anbiya\"", 
			"Surat Al Hajj", 
			"Surat Al Mu\"minun", 
			"Surat An Nur", 
			"Surat Al Furqan", 
			"Surat Ash Shu'ara\"", 
			"Surat An Naml", 
			"Surat Al Qasas", 
			"Surat Al 'Ankabut", 
			"Surat Ar Rum", 
			"Surat Luqman", 
			"Surat As Sajdah", 
			"Surat Al Ahzab", 
			"Surat Saba\"", 
			"Surat Fatir", 
			"Surat Ya Sin", 
			"Surat As Saffat", 
			"Surat Sad", 
			"Surat Az Zumar", 
			"Surat Ghafir",
			"Surat Fussilat", 
			"Surat Ash-Shura", 
			"Surat Az Zukhruf", 
			"Surat Ad Dukhan", 
			"Surat Al Jathiyah", 
			"Surat Al Ahqaf", 
			"Surat Muhammad", 
			"Surat Al Fath", 
			"Surat Al Hujurat", 
			"Surat Qaf", 
			"Surat Adh Dhariyat", 
			"Surat At Tur", 
			"Surat An Najm", 
			"Surat Al Qamar", 
			"Surat Ar Rahman", 
			"Surat Al Waqi'ah", 
			"Surat Al Hadid", 
			"Surat Al Mujadilah", 
			"Surat Al Hashr", 
			"Surat Al Mumtahanah", 
			"Surat As Saff", 
			"Surat Al Jumu'ah", 
			"Surat Al Munafiqun", 
			"Surat At Taghabun", 
			"Surat At Talaq", 
			"Surat At Tahrim", 
			"Surat Al Mulk", 
			"Surat Al Qalam", 
			"Surat Al Haqqah", 
			"Surat Al Ma'arij", 
			"Surat Nuh", 
			"Surat Al Jinn", 
			"Surat Al Muzzammil", 
			"Surat Al Muddaththir", 
			"Surat Al Qiyamah", 
			"Surat Al Insan", 
			"Surat Al Mursalat", 
			"Surat An Naba\"", 
			"Surat An Nazi'at", 
			"Surat 'Abasa", 
			"Surat At-Takwir", 
			"Surat Al Infitar", 
			"Surat Al Mutaffifin", 
			"Surat Al Inshiqaq", 
			"Surat Al Buruj", 
			"Surat At Tariq", 
			"Surat Al A'la", 
			"Surat Al Ghashiyah", 
			"Surat Al Fajr", 
			"Surat Al Balad", 
			"Surat Ash-Shams", 
			"Surat Al Layl", 
			"Surat Ad Duha", 
			"Surat Ash Sharh", 
			"Surat At Tin", 
			"Surat Al 'Alaq", 
			"Surat Al Qadr", 
			"Surat Al Bayyinah", 
			"Surat Az-Zalzalah", 
			"Surat Al 'Adiyat", 
			"Surat Al Qari'ah", 
			"Surat At Takathur", 
			"Surat Al 'Asr", 
			"Surat Al Humazah", 
			"Surat Al Fil", 
			"Surat Quraysh", 
			"Surat Al Ma'un", 
			"Surat Al Kawthar", 
			"Surat Al Kafirun", 
			"Surat An Nasr", 
			"Surat Al Masad", 
			"Surat Al Ikhlas", 
			"Surat Al Falaq",
			"Surat An Nas" };
}