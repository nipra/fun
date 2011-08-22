(ns email.main
  (:import [javax.mail Session Folder Flags Flags$Flag URLName]
           [javax.mail.search FlagTerm]
           [com.sun.mail.imap IMAPFolder IMAPStore IMAPSSLStore]))

(def *properties* (System/getProperties))

(comment (.setProperty *properties* "mail.store.protocol" "imaps")
         (def *session* (Session/getDefaultInstance *properties* nil))
         (def *store* (.getStore *session* "imaps"))
         (.connect *store* "imap.gmail.com" "prabhakar.nikhil@gmail.com" "PASSWORD")
         (def *folder* (.getFolder *store* "Inbox"))
         (.open *folder* Folder/READ_ONLY)

         (.getMessageCount *folder*)
         (def *msgs* (.getMessages *folder*))

         (def *msg1 (.getMessage *folder* 1)) ; Can be string or MimeMultipart
         (def *msg2 (.getMessage *folder* 8592))

         (def *content1 (.getContent *msg1)) ; Can be string or MimeMultipart
         (def *content2 (.getContent *msg2))

         (let [multipart-count (.getCount *content2)]     ; MimeMultipart
           (dotimes [n multipart-count]
             (let [body-part (.getBodyPart *content2 n)]
               (.removeBodyPart *content2 n)
               (.addBodyPart *content2 body-part))))

         (let [multipart-count (.getCount *content2)] ; MimeMultipart
           (apply str (for [n (range multipart-count)
                            :let [body-part (.getBodyPart *content2 n)]]
                        (.getContent body-part))))

         (def *flag-term1* (FlagTerm. (Flags. (Flags$Flag/SEEN)) false))
         (def *msgs2* (.search *folder* *flag-term1*)))


(comment
  (def *session* (Session/getDefaultInstance *properties* nil))
  (def *imap-store*
    (IMAPSSLStore. *session*
                   ;; new URLName("imap://myusername:mypassword@imap.gmail.com/")
                   (URLName. "imap" "imap.gmail.com" 993 nil "prabhakar.nikhil@gmail.com"
                             "Nikhil@Prabhakar")))
  (.connect *imap-store*)
  (def *imap-folder (.getFolder *imap-store* "Inbox"))
  (.open *imap-folder Folder/READ_ONLY)
  (.getUIDNext *imap-folder)

  (do
    (.close *imap-folder false)
    (.open *imap-folder Folder/READ_ONLY)
    (.getUIDNext *imap-folder)))

