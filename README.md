### 【プロジェクト概要】
[RIZINランキングDEMO](http://rizinff.net/)

格闘技観戦が趣味で、RIZINという格闘技団体のランキングや大会情報、選手名簿のサイトをインフラからアプリケーションまで一人で作成しました。

### 【こだわった点】
・スマホ レスポンシブ対応  
・勝敗に応じてポイントを奪い合う計算ロジックもオリジナルで作成  
・右上の選手名検索のキー入力時にAjaxでリアルタイムでDBへ検索し部分レンダリングを行っています。

### 【技術スタック】
デプロイ先：AWS EC2  
DB：PostgreSQL (Supabase)  
フレームワーク：SpringBoot  
CSSフレームワーク：BootStrap  
グラフツール：Chart.js
