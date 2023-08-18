export const API_BASE_URL = "http://localhost:8085/api"
import { TextEncoder, TextDecoder } from 'text-encoding-utf-8';

global.TextEncoder = TextEncoder;
global.TextDecoder = TextDecoder;