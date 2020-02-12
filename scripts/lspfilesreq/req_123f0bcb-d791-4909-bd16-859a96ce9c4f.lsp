(+ 12 34)
(defmacro slow-compiling-macro (arg) (dotimes (i 1000000) (incf i)) `(print ,arg)) (slow-compiling-macro 42)
