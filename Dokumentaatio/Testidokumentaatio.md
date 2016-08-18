#Testidokumentaatio

MiinaPaneeli ja Käyttöliittymä luokkia ei ole testattu automaattisesti, sillä ne sisältävät grafiikoita. Lisäksi miinan
paikka arvotaan, joten sitä ei voi testata automaattisesti.

Käyttöliittymä luokassa olevaa vaikeustaso valikkoa testasin käsin kokeilemalla, että tietystä vaikeustason nappulasta
luodaan tietynkokoinen Miinaharava peli.

MiinaPaneeli luokkaa testasin käsin luomalla hetkeksi testi pelin, joka oli 3x3 kokoinen ruudukko ja miina 
oli tunnetussa paikassa (1,1). Kun tiesin, missä miina sijaitsee, niin pystyin testaamaan siirraMiina() metodia.
Eli painoin miinaa heti ensimmäisenä, ja tarkistin, että miina ei enää ollut kyseisessä paikassa. Lisäksi tarkistin, että
muille ruuduille on tulleet miinan uutta paikkaa vastaavat arvot.

Testipelin avulla tarkistin myös, että pelin voitto ja häviö toimii, ja että silloin tulee halutut ilmoitukset yläreunaan.
Lisäksi tarkistin, että hävitessä pelin, kaikki avaamattomat ruudut aukeavat automaattisesti. Tällöin pelaaja
näkee, missä miinat olivat.

Yläreunassa olevaa valikkoa testasin käsin, painamalla siellä olevia nappuloita pelin eri vaiheissa. Käytettyjen
lippujen määrää testasin laittamalla ja poistamalla lippuja peliruudukosta. Tarkistin myös, että lippuja saa
laittaa maksimissaan yhtä monta, kuin pelilaudassa on miinoja.

Ruudun naapurien paljastusta (mikäli arvo on nolla) testasin käsin pelaamalla peliä useita kertoja, ja katsomalla
että naapurit avataan vain silloin, kun painan ruutua, jonka arvo on nolla. Varmistin myös, että tällöin ei
vahingossa avata miinaa.

Ruudun merkkausta avatuksi testasin myös käsin, painamalla ruutuja pelissä. Tarkistin myös, että ruutua ei voi
avata kahdesti. Lipun voi ottaa pois oikealla klikillä, jonka jälkeen ruudun voi vasta avata.
