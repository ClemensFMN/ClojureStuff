(defprotocol Printable
  (print-it [val]))

(extend-type String Printable
             (print-it [val]
               (.concat "String value: " val)))

(extend-type Long Printable
             (print-it [val]
               (.concat "Integer value: " (.toString val))))
