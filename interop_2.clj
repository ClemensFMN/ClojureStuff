(import [java.io File FilenameFilter])

;; we define a function which impleemnts the FileNameFilter interface
(defn suffix-filter [sfx]
  (reify FilenameFilter ; interface FilenameFilter
    (accept [this dir name] ; need to impleemnt method accept(File dir, String name)
      (.endsWith name sfx)))) ; method implementation

;; we define another function which uses File
;; the used Java API is
;; File constructor = File(String pathname)
;; list method = list(FilenameFilter filter)

(defn list-files [dir sfx]
  (seq (.list
        (File. dir)
        (suffix-filter sfx))))


(list-files "." "clj")
