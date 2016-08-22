#Rakennekuvaus

Miinaharava projekti sisältää neljä luokkaa: Käyttöliittymä, Miinapaneeli, Pelilauta ja Ruutu. Lisäksi Miinapaneeli sisältää
luokat Logiikka ja MouseListener.

**Käyttöliittymässä** luodaan aloitusikkuna, joka sisältää neljä nappia: Tosi helppo, Helppo, Keskivaikea ja Vaikea. 
Tosi helppo nappi aloittaa pelin, jonka pelilaudan koko on 3x3, jolloin peli sisältää vain yhden miinan. Helppo
nappi aloittaa taas pelin, jonka koko on 10x10 ja sisältää 20 miinaa. Keskivaikean pelin koko on 15x15 ja se 
sisältää 45 miinaa. Vaikean pelin koko on 18, ja se sisältää 64 miinaa. Vaikeustason valinnan jälkeen käyttöliittymä luokka luo Pelilauta olion ja Miinapaneeli olion, jolle annetaan parametrina pelilaudan 
koko ja Pelilauta olio. 

**RuudukonAlustus** luo Ruutu matriisin, joka on halutun pelilaudan kokoinen sekä asettaa satunnaisiin ruutuihin miinat.

**Pelilauta** asettaa RuudukonAlustus luokassa luoduille ruuduille arvot sen mukaan, kuinka monta miinaa on naapurissa. Lisäksi Pelilaudalla voi siirtää miinan paikkaa. Miinaa siirretään vain silloin, kun käyttäjä avaa ensimmäisen
ruudun, ja siinä on miina. Tämä tarkistus tehdään Logiikka luokassa. Käyttäjä ei kuitenkaan huomaa tätä siirtoa, joten peli jatkuu normaalisti.

**Ruutu** luokka sisältää tiedot, että onko kyseisessä ruudussa miina, lippu tai onko se avattu. Lisäksi Ruudussa
on tieto naapurissa olevien miinojen määrästä. Ruutu luokassa voi asettaa miinan, poistaa miinan, asettaa arvon,
asettaa lipun, poistaa lipun ja merkata ruudun avatuksi.

**MiinaPaneeli** luokassa luodaan miinaharava pelin grafiikat. Konstruktorissa luodaan pelille JMenu valikko 
ylänurkkaan, joka sisältää uusi peli ja sulje peli nappulat, ja lisäksi luodaan JButton matriisi, joka on pelilaudan kokoinen.
Kun jotain näistä JButtoneista painetaan, Logiikka ja MouseListener luokkien avulla tarkistetaan painoiko käyttäjä vasenta vai oikeaa hiiren näppäintä. 

**Logiikka** luokassa tarkastetaan, mitä tapahtuu kun painetaan hiiren vasenta tai oikeaa näppäintä.  
Jos käyttäjä painoi vasenta näppäintä, niin nappula avataan, jolloin paljastuu ruudun naapureissa olevien miinojen määrä.
Jos käyttäjä painoi oikeaa näppäintä, niin nappulan kohdalle laitetaan lippu. Lipun voi ottaa pois painamalla uudestaan
oikeaa näppäintä. Jokaisen vasemman näppäimen painalluksen jälkeen tarkistetaan, onko peli voitettu tai hävitty.
Peli voitetaan, jos kaikki miinattomat ruudut ovat avattu. Peli hävitään, jos avataan miinan sisältävä ruutu, tällöin
peeli vaa kaikki avaamattomat ruudut. Käyttäjä voi valikosta aloittaa uuden pelin tai sulkea pelin.
  
Jokaisen ruudun avauksen yhteydessä tarkistetaan mikäli klikatun ruudun yhdessäkään naapuriruudussa ei ole miinaa, 
niin peli paljastaa ensin kaikki siihen yhteydessä olevat ruudut, joiden naapurissa ei myöskään ole miinaa, 
sekä lisäksi tämän paljastetun alueen reunalla olevat ruudut, joiden naapurissa on miina.
