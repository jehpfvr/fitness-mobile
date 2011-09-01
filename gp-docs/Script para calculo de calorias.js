<!--
function Gasto(form) {
  Calorias = 0
  if (form.exercise.options[1].selected == true) { Calorias = 3.3  }; //arco e flexa
  if (form.exercise.options[2].selected == true) { Calorias = 5.7  }; //ballet
  if (form.exercise.options[3].selected == true) { Calorias = 6.9  }; //basquete
  if (form.exercise.options[4].selected == true) { Calorias = 2.1  }; //bilhar
  if (form.exercise.options[5].selected == true) { Calorias = 6.9  }; //box no ringue
  if (form.exercise.options[6].selected == true) { Calorias = 11.1 }; //box no treinamento
  if (form.exercise.options[7].selected == true) { Calorias = 4.0  }; //caminhada a passo normal (estrada de asfalto)
  if (form.exercise.options[8].selected == true) { Calorias = 4.1  }; //caminhada a passo normal (campos e colinas)
  if (form.exercise.options[9].selected == true) { Calorias = 4.1  }; //caminhada a passo normal (pista de grama)
  if (form.exercise.options[10].selected == true) { Calorias = 2.2 };  //canoagem - lazer
  if (form.exercise.options[11].selected == true) { Calorias = 5.2 };  //canoagem - competição
  if (form.exercise.options[12].selected == true) { Calorias = 8.5 }; //capoeira
  if (form.exercise.options[13].selected == true) { Calorias = 6.9 }; //cavalgar - galope
  if (form.exercise.options[14].selected == true) { Calorias = 5.5 }; //cavalgar - trote
  if (form.exercise.options[15].selected == true) { Calorias = 2.1 }; //cavalgar - marcha
  if (form.exercise.options[16].selected == true) { Calorias = 3.2 };  //ciclismo - lazer - 8 a 8,5 Km/h
  if (form.exercise.options[17].selected == true) { Calorias = 5.0 };  //ciclismo - lazer - 12,4 a 15 Km/h
  if (form.exercise.options[18].selected == true) { Calorias = 8.5 };  //ciclismo - mountain bike
  if (form.exercise.options[19].selected == true) { Calorias = 8.5 };  //ciclismo - corrida - competição
  if (form.exercise.options[20].selected == true) { Calorias = 1.2 }; //comendo - sentado
  if (form.exercise.options[21].selected == true) { Calorias = 8.2 }; //corrida - cross
  if (form.exercise.options[22].selected == true) { Calorias = 6.8 }; //corrida no plano - 11min30s por 1.609 Km
  if (form.exercise.options[23].selected == true) { Calorias = 9.7 }; //corrida no plano - 9min por 1.609 Km
  if (form.exercise.options[24].selected == true) { Calorias = 10.8}; //corrida no plano - 8min por 1.609 Km
  if (form.exercise.options[25].selected == true) { Calorias = 12.2}; //corrida no plano - 7min por 1.609 Km
  if (form.exercise.options[26].selected == true) { Calorias = 13.9}; //corrida no plano - 6min por 1.609 Km
  if (form.exercise.options[27].selected == true) { Calorias = 14.5}; //corrida no plano - 5min30s por 1.609 Km
  if (form.exercise.options[28].selected == true) { Calorias = 2.3 };  //cozinhar - feminino
  if (form.exercise.options[29].selected == true) { Calorias = 2.4 }; //cozinhar - masculino
  if (form.exercise.options[30].selected == true) { Calorias = 5.2 };  //dança - aeróbica moderada
  if (form.exercise.options[31].selected == true) { Calorias = 6.7 }; //dança - aeróbica intensa
  if (form.exercise.options[32].selected == true) { Calorias = 2.6 }; //dança de salão
  if (form.exercise.options[33].selected == true) { Calorias = 8.4 }; //dança - coreografia
  if (form.exercise.options[34].selected == true) { Calorias = 5.2 }; //dança - twist ou rebolado
  if (form.exercise.options[35].selected == true) { Calorias = 1.8 }; //desenho/pintura em pé
  if (form.exercise.options[36].selected == true) { Calorias = 1.1 }; //dormir
  if (form.exercise.options[37].selected == true) { Calorias = 6.1 };  //escalada de montanha - sem carga
  if (form.exercise.options[38].selected == true) { Calorias = 6.5 };  //escalada de montanha - com carga de 5 kg
  if (form.exercise.options[39].selected == true) { Calorias = 7.0 };  //escalada de montanha - com carga de 10 Kg
  if (form.exercise.options[40].selected == true) { Calorias = 7.4 };  //escalada de montanha - com carga de 20 Kg
  if (form.exercise.options[41].selected == true) { Calorias = 1.5 }; //escrever - sentado
  if (form.exercise.options[42].selected == true) { Calorias = 6.0 }; //esqui - neve dura no plano em velocidade moderada
  if (form.exercise.options[43].selected == true) { Calorias = 7.2 }; //esqui - neve dura no plano, andando
  if (form.exercise.options[44].selected == true) { Calorias = 13.7}; //esqui - neve dura - downhill, velocidade máxima
  if (form.exercise.options[45].selected == true) { Calorias = 4.9 }; //esqui - neve mole - lazer feminino
  if (form.exercise.options[46].selected == true) { Calorias = 5.6 }; //esqui - neve mole - lazer masculino
  if (form.exercise.options[47].selected == true) { Calorias = 7.8 }; //esqui aquático
  if (form.exercise.options[48].selected == true) { Calorias = 5.8 };  //exercícios no universal
  if (form.exercise.options[49].selected == true) { Calorias = 3.1 };  //faxina - feminino
  if (form.exercise.options[50].selected == true) { Calorias = 2.9 };  //faxina - masculino
  if (form.exercise.options[51].selected == true) { Calorias = 1.3 }; //ficar de pé
  if (form.exercise.options[52].selected == true) { Calorias = 1.1 }; //ficar sentado
  if (form.exercise.options[53].selected == true) { Calorias = 8.9 }; //frescobol
  if (form.exercise.options[54].selected == true) { Calorias = 6.4 }; //futebol 
  if (form.exercise.options[55].selected == true) { Calorias = 6.6 }; //futebol americano 
  if (form.exercise.options[56].selected == true) { Calorias = 4.2 }; //ginástica aeróbica
  if (form.exercise.options[57].selected == true) { Calorias = 4.2 }; //ginástica olímpica
  if (form.exercise.options[58].selected == true) { Calorias = 4.3 }; //golfe
  if (form.exercise.options[59].selected == true) { Calorias = 7.1 }; //handebol
  if (form.exercise.options[60].selected == true) { Calorias = 4.2 }; //hidroginástica
  if (form.exercise.options[61].selected == true) { Calorias = 6.7 }; //hóquei de campo
  if (form.exercise.options[62].selected == true) { Calorias = 6.3 }; //jardinagem - cavar com pá
  if (form.exercise.options[63].selected == true) { Calorias = 3.9 }; //jardinagem - capinar com enchada
  if (form.exercise.options[64].selected == true) { Calorias = 5.6 }; //jardinagem - cortar grama
  if (form.exercise.options[65].selected == true) { Calorias = 2.7 }; //jardinagem - capinar com ancinho
  if (form.exercise.options[66].selected == true) { Calorias = 8.5 }; //jiu-jitsu
  if (form.exercise.options[67].selected == true) { Calorias = 1.3 };  //jogo de carta
  if (form.exercise.options[68].selected == true) { Calorias = 9.8 }; //judô
  if (form.exercise.options[69].selected == true) { Calorias = 13.8}; //mergulho autônomo
  if (form.exercise.options[70].selected == true) { Calorias = 3.5 }; //musculação
  if (form.exercise.options[71].selected == true) { Calorias = 8.5 }; //natação - nado de costas
  if (form.exercise.options[72].selected == true) { Calorias = 8.1 }; //natação - nado de peito
  if (form.exercise.options[73].selected == true) { Calorias = 7.8 }; //natação - nado crowl - braçadas rápidas
  if (form.exercise.options[74].selected == true) { Calorias = 6.4 }; //natação - nado crowl - braçadas lentas
  if (form.exercise.options[75].selected == true) { Calorias = 6.1 }; //natação - nado com braçadas laterais
  if (form.exercise.options[76].selected == true) { Calorias = 8.5 }; //natação - passagens rápidas
  if (form.exercise.options[77].selected == true) { Calorias = 3.1 }; //natação - passagens normais
  if (form.exercise.options[78].selected == true) { Calorias = 6.7 }; //patinação no gelo
  if (form.exercise.options[79].selected == true) { Calorias = 3.1 }; //pescaria
  if (form.exercise.options[80].selected == true) { Calorias = 4.3 };  //pesos livres
  if (form.exercise.options[81].selected == true) { Calorias = 4.9 };  //peteca
  if (form.exercise.options[82].selected == true) { Calorias = 8.1 }; //pular corda - 70/minuto
  if (form.exercise.options[83].selected == true) { Calorias = 8.2 }; //pular corda - 80/minuto
  if (form.exercise.options[84].selected == true) { Calorias = 8.9 }; //pular corda - 125/minuto
  if (form.exercise.options[85].selected == true) { Calorias = 9.9 }; //pular corda - 145/minuto
  if (form.exercise.options[86].selected == true) { Calorias = 7.8 }; //remo
  if (form.exercise.options[87].selected == true) { Calorias = 2.9 }; //shopping - feminino
  if (form.exercise.options[88].selected == true) { Calorias = 3.1 }; //shopping - masculino
  if (form.exercise.options[89].selected == true) { Calorias = 10.6}; //squash
  if (form.exercise.options[90].selected == true) { Calorias = 5.7 }; //surf
  if (form.exercise.options[91].selected == true) { Calorias = 5.5 }; //tênis
  if (form.exercise.options[92].selected == true) { Calorias = 3.4 }; //tênis de mesa (ping-pong)
  if (form.exercise.options[93].selected == true) { Calorias = 6.6 };  //treinamento em circuito
  if (form.exercise.options[94].selected == true) { Calorias = 2.5 }; //voleibol
  if (form.exercise.options[95].selected == true) { Calorias = 5.0 }; //windsurf

  form.Total.value = ((Calorias * form.Peso.value)/50) * form.Tempo.value
}
// -->
