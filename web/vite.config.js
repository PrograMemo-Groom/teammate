import react from '@vitejs/plugin-react';
import {defineConfig} from 'vite';

// defineConfig 함수로 Vite 설정 정의
export default defineConfig({
    // Vite에서 사용할 플러그인 목록
    plugins: [react()],
    // Vite 개발 서버 설정 정의
    server: {
        // Proxy 설정
        proxy: {
            // 경로가 "/api" 로 시작하는 요청을 대상으로 proxy 설정
            '/api': {
                // 요청 전달 대상 서버 주소 설정
                target: 'http://localhost:8080',
                // 요청 헤더 host 필드 값을 대상 서버의 호스트 이름으로  변경
                changeOrigin: true,
                // 요청 경로에서 '/api' 제거
                // rewrite: (path) => path.replace(/^\/api/, ''),
                // SSL 인증서 검증 무시
                secure: false,
                // WebSocket 프로토콜 사용
                ws: true,
            },
        },
    },
});
