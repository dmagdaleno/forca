(ns forca.core
  (:gen-class))

(def total-de-vidas 6)
(def palavra-secreta "MELANCIA")

(defn perdeu [] (print "Você perdeu!\n"))

(defn ganhou [] (print "Você ganhou!\n"))

(defn letras-faltantes [palavra acertos]
  (remove (fn [letra] (contains? acertos (str letra))) palavra))

(defn acertou-a-palavra-toda? [palavra acertos] 
  (empty? (letras-faltantes palavra acertos)))

(defn le-letra! [] (read-line))

(defn acertou? [chute palavra] (.contains palavra chute))

(defn imprime-forca [vidas palavra acertos]
  (print (str (char 27) "[2J"))
  (print (str (char 27) "[;H"))
  (println "Vidas " vidas)
  (doseq [letra (seq palavra)]
    (if (contains? acertos (str letra))
      (print letra " ") (print "_" " ")))
  (println))

(defn jogo [vidas palavra acertos]
  (imprime-forca vidas palavra acertos)
  (cond
    (= vidas 0) (perdeu)
    (acertou-a-palavra-toda? palavra acertos) (ganhou)
    :else   
    (let [chute (le-letra!)]
      (if (acertou? chute palavra)
        (recur vidas palavra (conj acertos chute))
        (recur (dec vidas) palavra acertos)))))

(defn inicia-jogo []
  (jogo total-de-vidas palavra-secreta #{}))

(defn -main [& args]
  (inicia-jogo))